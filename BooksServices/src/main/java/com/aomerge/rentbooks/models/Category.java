package com.aomerge.rentbooks.models;

import com.aomerge.rentbooks.config.type.CategoryType;
import com.aomerge.rentbooks.config.type.CategoryTypeColor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Setter
@Getter
public class Category {
    @Id
    private String id;
    public CategoryType name;
    public CategoryTypeColor color;
    @DBRef
    public List<Book> books;
}

