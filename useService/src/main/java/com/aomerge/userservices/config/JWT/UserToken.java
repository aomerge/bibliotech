package com.aomerge.userservices.config.JWT;

import com.aomerge.userservices.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String token;
    private User user;
}
