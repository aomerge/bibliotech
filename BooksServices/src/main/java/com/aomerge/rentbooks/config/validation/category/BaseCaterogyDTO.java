package com.aomerge.rentbooks.config.validation.category;

import com.aomerge.rentbooks.config.type.CategoryType;
import com.aomerge.rentbooks.config.type.CategoryTypeColor;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdateByBook;
import com.aomerge.rentbooks.models.Book;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BaseCaterogyDTO {
    @NotNull(groups = OnUpdate.class, message = "Id is required")
    public String Id;

    @NotNull(groups = OnCreate.class, message = "Category Type Is required")
    public CategoryType name;

    @NotNull(groups = OnCreate.class, message = "Category Type Is required")
    public CategoryTypeColor color;

    @NotNull(groups = OnUpdateByBook.class, message = "Book is required")
    public List<Book> books;


    public @NotNull(groups = OnUpdate.class, message = "Id is required") String getId() {
        return Id;
    }

    public void setId(@NotNull(groups = OnUpdate.class, message = "Id is required") String id) {
        Id = id;
    }

    public @NotNull(groups = OnCreate.class, message = "Category Type Is required") CategoryType getName() {
        return name;
    }

    public void setName(@NotNull(groups = OnCreate.class, message = "Category Type Is required") CategoryType name) {
        this.name = name;
    }

    public @NotNull(groups = OnCreate.class, message = "Category Type Is required") CategoryTypeColor getColor() {
        return color;
    }

    public void setColor(@NotNull(groups = OnCreate.class, message = "Category Type Is required") CategoryTypeColor color) {
        this.color = color;
    }

    public @NotNull(groups = OnUpdateByBook.class, message = "Book is required") List<Book> getBooks() {
        return books;
    }

    public void setBooks(@NotNull(groups = OnUpdateByBook.class, message = "Book is required") List<Book> books) {
        this.books = books;
    }
}
