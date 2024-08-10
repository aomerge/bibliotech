package com.aomerge.rentbooks.config.validation.baseBookExisten;


import com.aomerge.rentbooks.config.type.BookStatus;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBookInExistenDTO {
    @NotNull(groups = {OnUpdate.class}, message = "The idBook is required")
    private String id;
    @NotNull(groups = {OnCreate.class}, message = "The idBook is required")
    private String idBook;
    @NotNull(groups = {OnCreate.class}, message = "The quantity is required")
    @Min(value = 1, groups = {OnCreate.class}, message = "The quantity must be greater than 0")
    private int quantity;
    @NotNull(groups = {OnCreate.class}, message = "The status is required")
    private BookStatus status;

}
