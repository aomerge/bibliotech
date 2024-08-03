package com.aomerge.rentbooks.config.validation.branchOffice;

import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BaseBranchOfficeDTO {
    @NotNull(groups = {OnUpdate.class}, message = "Id is required")
    protected String idOffice;

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

    public @NotNull(groups = {OnUpdate.class}, message = "Id is required") String getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(@NotNull(groups = {OnUpdate.class}, message = "Id is required") String idOffice) {
        this.idOffice = idOffice;
    }

    public @NotNull(groups = {OnCreate.class}, message = "Name is required") @Pattern(groups = {OnCreate.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric") @Size(groups = {OnCreate.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(groups = {OnCreate.class}, message = "Name is required") @Pattern(groups = {OnCreate.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric") @Size(groups = {OnCreate.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(groups = OnCreate.class, message = "Addres is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotNull(groups = OnCreate.class, message = "Addres is required") String address) {
        this.address = address;
    }

    public @NotNull(groups = OnCreate.class, message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotNull(groups = OnCreate.class, message = "City is required") String city) {
        this.city = city;
    }

    public @NotNull(groups = OnCreate.class, message = "Country is required") String getCountry() {
        return country;
    }

    public void setCountry(@NotNull(groups = OnCreate.class, message = "Country is required") String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
