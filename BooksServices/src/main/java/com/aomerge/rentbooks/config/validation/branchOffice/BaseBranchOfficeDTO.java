package com.aomerge.rentbooks.config.validation.branchOffice;

import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBranchOfficeDTO {
    @NotNull(groups = {OnUpdate.class}, message = "Id is required")
    protected String id;

    @NotNull(groups = {OnCreate.class}, message = "Name is required")
    @Pattern(groups = {OnCreate.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric")
    @Size(groups = {OnCreate.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters")
    protected String name;

    @NotNull(groups = OnCreate.class, message = "Addres is required")
    protected String address;

    @NotNull(groups = OnCreate.class, message = "City is required")
    protected String city;

    @NotNull(groups = OnCreate.class, message = "Country is required")
    protected String country;

    protected String phone;

}
