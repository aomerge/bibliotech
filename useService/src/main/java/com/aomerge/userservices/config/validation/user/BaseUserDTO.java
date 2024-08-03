package com.aomerge.userservices.config.validation.user;

import com.aomerge.userservices.config.validation.groups.OnCreate;
import com.aomerge.userservices.config.validation.groups.OnUpdate;
import com.aomerge.userservices.models.Access;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseUserDTO {
    @NotNull(groups = {OnUpdate.class}, message = "El id es requerido")
    private String id;
    @NotNull(groups = {OnCreate.class}, message = "El nombre es requerido")
    private String name;
    @NotNull(groups = {OnCreate.class}, message = "El email es requerido")
    private String email;
    @NotNull(groups = {OnCreate.class}, message = "La contraseña es requerida")
    private String password;
    @NotNull(groups = {OnCreate.class}, message = "El rol es requerido")
    private String role;
    private Access access;
}
