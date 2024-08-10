package com.aomerge.rentbooks.models;

import com.aomerge.rentbooks.controllers.CategoryController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "books")
@Setter
@Getter
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private String image;
    private String status;
    private int access;
    private int ISBN;
    private int year;
    private String categoryId;
    @DBRef
    private Tag tagsId;
    @DBRef
    private BookDigital bookDigitalId;

    private Date publishedAt;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
