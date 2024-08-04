package com.aomerge.rentbooks.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "booksInExisten")
public class BooksInExisten {
    @Id
    private String id;
    @Indexed(unique = true)
    private String idBook;
    private int quantity;
    private BookStatus status;
    @CreatedDate
    private String createdAt;
    @LastModifiedDate
    private String updatedAt;

    public enum BookStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        RESERVED
    }
}
