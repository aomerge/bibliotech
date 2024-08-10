package com.aomerge.rentbooks.services;

import com.aomerge.rentbooks.config.exeptions.CustomAuthorizationException;
import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.category.BaseCaterogyDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdateByBook;
import com.aomerge.rentbooks.models.Book;
import com.aomerge.rentbooks.models.Category;
import com.aomerge.rentbooks.repository.BooksRepository;
import com.aomerge.rentbooks.repository.BranchOfficeRepository;
import com.aomerge.rentbooks.repository.CategoryRepository;
import com.aomerge.rentbooks.services.DTO.CategoryDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryDTO {

    private final CategoryRepository categoryRepository;
    private final BooksRepository bookRepository;
    private final Validator validator;

    @Autowired
    public CategoryService ( CategoryRepository categoryRepository, BooksRepository booksRepository ,Validator validator ){
        this.bookRepository=booksRepository;
        this.validator=validator;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Category getCategory( String id) throws UserNotExistException {
        return categoryRepository.findById(id).orElse(null);
        //return categoryRepository.findById(id).orElseThrow(()-> new UserNotExistException(404,"Category not found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(401, violationHeader);
        }
        // validation Body
        Set<ConstraintViolation<BaseCaterogyDTO>> violationsBody = validator.validate(category, OnCreate.class);
        if(!violationsBody.isEmpty()) {
            throw new UserBadRequest(400, violationsBody);
        }
        Category categoryRequest = new Category();
        categoryRequest.setName(category.getName());
        categoryRequest.setColor(category.getColor());
        categoryRepository.save(categoryRequest);
        return categoryRequest;
    }

    @Override
    public void putBook(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) throws UserNotExistException {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(401,violationHeader);
        }
        // validation Body
        Set<ConstraintViolation<BaseCaterogyDTO>> violationsBody = validator.validate(category, OnUpdateByBook.class);
        if(!violationsBody.isEmpty()) {
            throw new UserBadRequest(401, violationsBody);
        }
        // get the books
        List<Book> books = bookRepository.findAllById(category.getBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList()));
        // get the category and add the books
        Category categoryRequest = categoryRepository.findById(category.getId()).map(
                categoryBook -> {
                    // get the existing books
                    List<Book> existingBooks = categoryBook.getBooks();
                    // if the category does not have books, create a new list
                    if (existingBooks == null) {
                        existingBooks = new ArrayList<>();
                        categoryBook.setBooks(existingBooks);
                    }
                    // set the category to the books
                    for (Book newBook : books) {
                        if (existingBooks.stream().anyMatch(book -> book.getId().equals(newBook.getId()))) {
                            try {
                                throw new UserNotExistException(404, "Book already exists in the category");
                            } catch (UserNotExistException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    // if the book already exists in the category, throw an exception
                    existingBooks.stream().filter(book -> category.getBooks().stream()
                            .anyMatch(newBook -> newBook.getId().equals(book.getId()) ) )
                            .findFirst()
                            .ifPresent(book -> {
                                try {
                                    throw new UserNotExistException(404, "Book already exists in the category");
                                } catch (UserNotExistException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                    // add the new books to the existing list
                    existingBooks.addAll(books);

                    return categoryBook;
                })
                .orElseThrow(()-> new UserNotExistException(404,"Category not found"));
        categoryRepository.save(categoryRequest);
        for (Book book : books) {
            if (book.getCategoryId() == null ) {
                book.setCategoryId(categoryRequest.getId());
                bookRepository.save(book); // Asegurarse de que el libro se guarde con la categoría actualizada
            }
        }
    }

    @Override
    public void update(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) throws UserNotExistException {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(401,violationHeader );
        }
        // validation Body
        Set<ConstraintViolation<BaseCaterogyDTO>> violationsBody = validator.validate(category, OnUpdate.class);
        if(!violationsBody.isEmpty()) {
            throw new UserBadRequest(401,violationsBody);
        }

        Category categoryRequest = categoryRepository.findById(category.getId()).map(
                cat -> {
                    cat.setName(category.getName());
                    cat.setColor(category.getColor());
                    return cat;
                }
                )
                .orElseThrow(()-> new UserNotExistException(404,"Category not found"));

        categoryRepository.save(categoryRequest);

    }

    @Override
    public void deleted(HeaderValidationDTO authorizationHeader, String id) throws UserNotExistException {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(401,violationHeader.toString() );
        }

        Category categoryRequest = categoryRepository.findById(id).orElseThrow(()-> new UserNotExistException(404,"Category not found"));
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Book> getCategoryBooks(String id) {

        Category category =  categoryRepository.findById(id).orElseThrow(()-> new UserBadRequest(404, "category not fiend"));
        return category.getBooks();
    }

    @Override
    public void deletedBook(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) throws UserNotExistException {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(401,violationHeader.toString() );
        }
        // validation Body
        Set<ConstraintViolation<BaseCaterogyDTO>> violationsBody = validator.validate(category, OnUpdateByBook.class);
        if(!violationsBody.isEmpty()) {
            throw new UserBadRequest(401,violationsBody.toString());
        }
        List<Book> books = bookRepository.findAllById(category.getBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList()));

        Category categoryRequest = categoryRepository.findById(category.getId()).map(
                        categoyBook -> {
                            List<Book> existingBooks = categoyBook.getBooks();
                            if (existingBooks == null) {
                                existingBooks = new ArrayList<>();
                            }
                            try {
                                Optional.ofNullable(existingBooks.stream()
                                        .filter(item -> category.getBooks().stream()
                                                .anyMatch(newBook -> newBook.getId().equals(item.getId())))
                                        .findFirst().orElseThrow(() -> new UserNotExistException(404, "Book not found in the category")));
                            }catch (UserNotExistException e){
                                throw new RuntimeException(e);
                            }

                            existingBooks.removeIf(book -> category.getBooks().stream()
                                    .anyMatch(newBook -> newBook.getId().equals(book.getId())));

                            categoyBook.setBooks(existingBooks);
                            return categoyBook;
                        })
                .orElseThrow(()-> new UserNotExistException(404,"Category not found"));

        categoryRepository.save(categoryRequest);
    }
}
