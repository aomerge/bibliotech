package com.aomerge.userservices.services;

import com.aomerge.userservices.config.exeptions.CustomAuthorizationException;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.models.User;
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
    private final Validator validator;

    @Autowired
    public UserService(UsersRepository usersRepository, Validator validator) {
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
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
