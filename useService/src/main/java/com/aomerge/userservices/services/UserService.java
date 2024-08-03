package com.aomerge.userservices.services;

import com.aomerge.userservices.config.JWT.JWToken;
import com.aomerge.userservices.config.JWT.UserToken;
import com.aomerge.userservices.config.exeptions.CustomAuthorizationException;
import com.aomerge.userservices.config.exeptions.UserBadRequest;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.config.validation.groups.OnCreate;
import com.aomerge.userservices.config.validation.user.BaseUserDTO;
import com.aomerge.userservices.models.Access;
import com.aomerge.userservices.models.User;
import com.aomerge.userservices.repository.AccessRepository;
import com.aomerge.userservices.repository.UsersRepository;
import com.aomerge.userservices.services.serviceDTO.UserDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDTO {

    private final UsersRepository usersRepository;
    private final AccessRepository accessRepository;
    private final Validator validator;

    @Autowired
    public UserService(UsersRepository usersRepository,AccessRepository accessRepository, Validator validator) {
        this.accessRepository = accessRepository;
        this.usersRepository = usersRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAllUsers(HeaderValidationDTO token) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new CustomAuthorizationException(401, "header de autorización inválido");
        }
        return usersRepository.findAll();
    }

    @Override
    public User getUserById(HeaderValidationDTO token, String id) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new CustomAuthorizationException(401, "header de autorización inválido");
        }
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public UserToken login(String email, String password) {
        if (usersRepository.findByEmail(email) == null) {
            throw new UserBadRequest(400, "Usuario no encontrado");
        }
        User userResponse = usersRepository.findByEmailAndPassword(email, password);
        if (userResponse == null) {
            throw new UserBadRequest(400, "Contraseña incorrecta");
        }
        Access access = accessRepository.findByUserId(userResponse.getId())
                .orElseThrow(()-> new UserBadRequest(400, "Usuario no encontrado"));


        UserToken userToken = new UserToken();
        String jwToken = JWToken.CreateTokenUser(userResponse.getId(),access.getBranchOffice() ,access.getAccesBinary() );
        userToken.setToken(jwToken);
        userToken.setUser(userResponse);

        return userToken;
    }

    @Override
    public User save(BaseUserDTO user) {
        // Validar el objeto
        Set<ConstraintViolation<BaseUserDTO>> violations = validator.validate(user, OnCreate.class);
        if (!violations.isEmpty()) {
            throw new UserBadRequest(400, violations.toString());
        }

        User userResponse = new User();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setRole(user.getRole());
        return usersRepository.save(userResponse);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
