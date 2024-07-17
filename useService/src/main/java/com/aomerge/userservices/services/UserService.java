package com.aomerge.userservices.services;

import com.aomerge.userservices.services.serviceDTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDTO {
    private String user;

    public String getUser() {
        return "User";
    }
    public String getUserById() {
        return "UserById";
    }
}
