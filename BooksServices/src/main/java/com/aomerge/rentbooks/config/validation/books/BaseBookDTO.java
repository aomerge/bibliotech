package com.aomerge.rentbooks.config.validation.books;

import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdateAll;
import com.aomerge.rentbooks.controllers.CategoryController;
import com.aomerge.rentbooks.models.BookDigital;
import com.aomerge.rentbooks.models.Tag;
import jakarta.validation.constraints.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class BaseBookDTO {

    @NotNull(groups = {OnUpdate.class, OnUpdateAll.class}, message = "Id is required")
    protected String idBook;

    @NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "Title is required")
    @Pattern( groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric")
    @Size(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters")
    protected String title;

    @NotNull(groups = OnUpdateAll.class, message ="Description is required")
    @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Description must be alphanumeric")
    protected String description;

    @NotNull(groups = OnUpdateAll.class, message = "Author is required")
    @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class},regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Author must be alphanumeric")
    protected String author;

    @NotNull(groups = OnUpdateAll.class, message = "Year is required")
    @Max(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class},value = 2024, message = "Year must be in the past")
    @Min(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class},value = 1500, message = "Year must be in the past")
    protected int year;

    @NotNull(groups = OnUpdateAll.class, message = "Editorial is required")
    protected String image;

    @NotNull(groups = OnUpdateAll.class, message = "Access is required")
    protected int access;

    @NotNull(groups = OnUpdateAll.class, message = "Status is required")
    private CategoryController categoryId;

    @NotNull(groups = OnUpdateAll.class, message = "Tags is required")
    private Tag tagsId;

    @NotNull(groups = OnUpdateAll.class, message = "Digital Book is required")
    private BookDigital bookDigitalId;

    @NotNull(groups = OnUpdateAll.class, message = "Published At is required")
    private Date publishedAt;

    @NotNull(groups = OnUpdateAll.class, message = "Editorial is required")
    @NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "ISBN is required")
    protected int ISBN;

    public @NotNull(groups = {OnUpdate.class, OnUpdateAll.class}, message = "Id is required") String getIdBook() {
        return idBook;
    }

    public void setIdBook(@NotNull(groups = {OnUpdate.class, OnUpdateAll.class}, message = "Id is required") String idBook) {
        this.idBook = idBook;
    }

    public @NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "Title is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric") @Size(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "Title is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Title must be alphanumeric") @Size(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, min = 5, max = 50, message = "Title must be between 1 and 100 characters") String title) {
        this.title = title;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Description is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Description must be alphanumeric") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(groups = OnUpdateAll.class, message = "Description is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Description must be alphanumeric") String description) {
        this.description = description;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Author is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Author must be alphanumeric") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull(groups = OnUpdateAll.class, message = "Author is required") @Pattern(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, regexp = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑüÜ]*$", message = "Author must be alphanumeric") String author) {
        this.author = author;
    }

    @NotNull(groups = OnUpdateAll.class, message = "Year is required")
    @Max(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, value = 2024, message = "Year must be in the past")
    @Min(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, value = 1500, message = "Year must be in the past")
    public int getYear() {
        return year;
    }

    public void setYear(@NotNull(groups = OnUpdateAll.class, message = "Year is required") @Max(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, value = 2024, message = "Year must be in the past") @Min(groups = {OnCreate.class, OnUpdate.class, OnUpdateAll.class}, value = 1500, message = "Year must be in the past") int year) {
        this.year = year;
    }

    @NotNull(groups = OnUpdateAll.class, message = "Editorial is required")
    @NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "ISBN is required")
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(@NotNull(groups = OnUpdateAll.class, message = "Editorial is required") @NotNull(groups = {OnCreate.class, OnUpdateAll.class}, message = "ISBN is required") int ISBN) {
        this.ISBN = ISBN;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Editorial is required") String getImage() {
        return image;
    }

    public void setImage(@NotNull(groups = OnUpdateAll.class, message = "Editorial is required") String image) {
        this.image = image;
    }

    @NotNull(groups = OnUpdateAll.class, message = "Access is required")
    public int getAccess() {
        return access;
    }

    public void setAccess(@NotNull(groups = OnUpdateAll.class, message = "Access is required") int access) {
        this.access = access;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Status is required") CategoryController getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(groups = OnUpdateAll.class, message = "Status is required") CategoryController categoryId) {
        this.categoryId = categoryId;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Tags is required") Tag getTagsId() {
        return tagsId;
    }

    public void setTagsId(@NotNull(groups = OnUpdateAll.class, message = "Tags is required") Tag tagsId) {
        this.tagsId = tagsId;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Digital Book is required") BookDigital getBookDigitalId() {
        return bookDigitalId;
    }

    public void setBookDigitalId(@NotNull(groups = OnUpdateAll.class, message = "Digital Book is required") BookDigital bookDigitalId) {
        this.bookDigitalId = bookDigitalId;
    }

    public @NotNull(groups = OnUpdateAll.class, message = "Published At is required") Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(@NotNull(groups = OnUpdateAll.class, message = "Published At is required") Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}

