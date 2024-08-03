package com.aomerge.userservices.services.serviceDTO;

import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.models.User;

import java.util.List;

public interface UserDTO {
    public List<User> getAllUsers(HeaderValidationDTO token);
    public User getUserById(String id);
    public User login(String email, String password);
    public User save(User user);
    public User update(User user);
    public void delete(String id);
}
