package com.aomerge.userservices.config.validation.access;

import com.aomerge.userservices.config.validation.groups.OnCreate;
import com.aomerge.userservices.config.validation.groups.OnUpdate;
import com.aomerge.userservices.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseAccessDTO {
    @NotNull( groups = OnUpdate.class, message = "El id es requerido")
    private String id;

    private String branchOffice;

    private byte accesBinary;

    @NotNull( groups = {OnCreate.class}, message = "El usuario es requerido")
    private User userId;

    @NotNull( groups = {OnUpdate.class}, message = "El rol es requerido")
    private String role;
}
