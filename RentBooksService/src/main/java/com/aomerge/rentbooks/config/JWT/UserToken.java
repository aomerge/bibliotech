package com.aomerge.rentbooks.config.JWT;

import com.aomerge.rentbooks.models.BooksInExisten;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String token;
    private BooksInExisten booksInExisten;
}
