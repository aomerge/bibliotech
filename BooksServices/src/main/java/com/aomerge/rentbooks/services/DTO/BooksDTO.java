package com.aomerge.rentbooks.services.DTO;

import com.aomerge.rentbooks.models.Book;

public interface BooksDTO {
    public Book TestCreateBook();
    public String createBook(Book book);
    public Book createSampleBook();
}
