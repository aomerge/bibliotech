package com.aomerge.rentbooks.services;

import com.aomerge.rentbooks.Repository.BookInExistenRepositry;
import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.validation.baseBookExisten.BaseBookInExistenDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.models.BooksInExisten;
import com.aomerge.rentbooks.services.DTO.BookExistentDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookExistentService implements BookExistentDTO {

    private final BookInExistenRepositry bookInExistenRepositry;
    private final Validator validator;

    @Autowired
    public BookExistentService( BookInExistenRepositry bookInExistenRepositry ,Validator validator) {
        this.validator = validator;
        this.bookInExistenRepositry = bookInExistenRepositry;
    }

    @Override
    public BooksInExisten findById(HeaderValidationDTO token, String id) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        return bookInExistenRepositry.findById(id).orElse(null);
    }

    @Override
    public Optional<BooksInExisten> findByBookId(HeaderValidationDTO token, String bookId) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        return bookInExistenRepositry.findByIdBook(bookId);
    }

    @Override
    public BooksInExisten save(HeaderValidationDTO token, BaseBookInExistenDTO booksInExisten) {
        // Validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        // Validation Body
        Set<ConstraintViolation<BaseBookInExistenDTO>> violationBody = validator.validate(booksInExisten, OnCreate.class);
        if (!violationBody.isEmpty()) {
            throw new UserBadRequest(400, violationBody);
        }
        BooksInExisten booksInExistenModel = new BooksInExisten();
        booksInExistenModel.setIdBook(booksInExisten.getIdBook());
        booksInExistenModel.setQuantity(booksInExisten.getQuantity());
        booksInExistenModel.setStatus(booksInExisten.getStatus());
        return bookInExistenRepositry.save(booksInExistenModel);
    }

    @Override
    public BooksInExisten update(HeaderValidationDTO token,BaseBookInExistenDTO booksInExisten) {
        // Validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        // Validation Body
        Set<ConstraintViolation<BaseBookInExistenDTO>> violationBody = validator.validate(booksInExisten, OnUpdate.class);
        if (!violationBody.isEmpty()) {
            throw new RuntimeException("Datos de libro inválidos");
        }
        BooksInExisten booksInExistenModel = bookInExistenRepositry.findById(booksInExisten.getId())
                .map(
                        book -> {
                            book.setIdBook(booksInExisten.getIdBook());
                            book.setStatus(booksInExisten.getStatus());
                            return bookInExistenRepositry.save(book);
                        }
                )
                .orElseThrow(()-> new RuntimeException("Libro no encontrado"));

        return bookInExistenRepositry.save(booksInExistenModel);
    }

    @Override
    public void updateAddQuantity(HeaderValidationDTO token, String id, int quantity) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        bookInExistenRepositry.findByIdBook(id)
                .map(
                        book -> {
                            book.setQuantity(book.getQuantity() + quantity);
                            return bookInExistenRepositry.save(book);
                        }
                )
                .orElseThrow(()-> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public void delete(HeaderValidationDTO token, String id) {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(token);
        if (!violationHeader.isEmpty()) {
            throw new RuntimeException("header de autorización inválido");
        }
        bookInExistenRepositry.deleteById(id);
    }
}
