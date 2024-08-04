package com.aomerge.userservices.services;

import com.aomerge.userservices.config.access.AccesBinary;
import com.aomerge.userservices.config.exeptions.UserNotExistException;
import com.aomerge.userservices.config.validation.access.BaseAccessDTO;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.models.Access;
import com.aomerge.userservices.models.User;
import com.aomerge.userservices.repository.AccessRepository;
import com.aomerge.userservices.repository.UsersRepository;
import com.aomerge.userservices.services.serviceDTO.AccessDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccessService implements AccessDTO {
    private final UsersRepository usersRepository;
    private AccessRepository accessRepository;
    private final Validator validator;

    @Autowired
    public AccessService(AccessRepository accessRepository, UsersRepository usersRepository, Validator validator) {
        this.accessRepository = accessRepository;
        this.usersRepository = usersRepository;
        this.validator = validator;
    }


    @Override
    public Access getAll() {
        return null;
    }

    @Override
    public Access save(HeaderValidationDTO token, BaseAccessDTO access) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }

        User userRequest = usersRepository.findById(access.getUserId().getId()).orElse(null);
        if (userRequest == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Access accessModel = new Access();
        accessModel.setBranchOffice(access.getBranchOffice());
        AccesBinary accesBinary = new AccesBinary();
        accesBinary.savePermition(userRequest.getRole());
        accessModel.setAccesBinary(accesBinary.getPermition());
        accessModel.setUserId(access.getUserId());
        return accessRepository.save(accessModel);
    }

    @Override
    public Access update(HeaderValidationDTO token, BaseAccessDTO access) throws UserNotExistException {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        User userRequest = usersRepository.findById(access.getUserId().getId()).map(
                user -> {
                    user.setRole(access.getRole());
                    return usersRepository.save(user);
                }
        ).orElseThrow(() -> new UserNotExistException(404,"Usuario no encontrado"));

         Access accessModel = accessRepository.findById(access.getId()).map(
                accessModel1 -> {
                    AccesBinary accesBinary = new AccesBinary();
                    accesBinary.savePermition(userRequest.getRole());
                    accessModel1.setAccesBinary(accesBinary.getPermition());
                    accessModel1.setUserId(access.getUserId());
                    return accessRepository.save(accessModel1);
                }
        ).orElseThrow(() -> new UserNotExistException(404,"Acceso no encontrado"));
        return accessModel;

    }

    @Override
    public void delete(HeaderValidationDTO token, String id) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        accessRepository.deleteById(id);

    }


}
