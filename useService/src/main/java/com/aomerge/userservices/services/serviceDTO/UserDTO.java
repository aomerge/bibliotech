package com.aomerge.userservices.services.serviceDTO;

import com.aomerge.userservices.config.JWT.UserToken;
import com.aomerge.userservices.config.validation.global.HeaderValidationDTO;
import com.aomerge.userservices.config.validation.user.BaseUserDTO;
import com.aomerge.userservices.models.User;

import java.util.List;

public interface UserDTO {
    public List<User> getAllUsers(HeaderValidationDTO token);
    public User getUserById(HeaderValidationDTO token, String id);
    public UserToken login(String email, String password);
    public User save(BaseUserDTO user);
    public User update( HeaderValidationDTO token ,BaseUserDTO user);
    public void delete(HeaderValidationDTO token, String id);
}
