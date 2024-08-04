package com.aomerge.rentbooks.services.DTO;

import com.aomerge.rentbooks.config.validation.baseBookExisten.BaseBookInExistenDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.models.BooksInExisten;

import java.util.Optional;

public interface BookExistentDTO {
    public BooksInExisten findById(HeaderValidationDTO token, String id);
    public Optional<BooksInExisten> findByBookId(HeaderValidationDTO token, String bookId);
    public BooksInExisten save(HeaderValidationDTO token , BaseBookInExistenDTO booksInExisten);
    public BooksInExisten update(HeaderValidationDTO token, BaseBookInExistenDTO booksInExisten);
    public void updateAddQuantity(HeaderValidationDTO token, String id, int quantity);
    public void delete(HeaderValidationDTO token, String id);
}
