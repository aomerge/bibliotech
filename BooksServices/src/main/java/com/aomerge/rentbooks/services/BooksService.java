package com.aomerge.rentbooks.services;
import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.books.BaseBookDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdateAll;
import com.aomerge.rentbooks.services.DTO.BooksDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aomerge.rentbooks.repository.BooksRepository;
import com.aomerge.rentbooks.models.Book;

import java.util.List;
import java.util.Set;

@Service
public class BooksService  implements BooksDTO {

    /** this is the repository of the books
     * */
    private BooksRepository booksRepository;

    /**this metod is used to validate the data of the book
     * */
    private final Validator validator;

    /**this is the constructor of the class
     * @param booksRepository this is the repository of the books
     * @param validator this metod is used to validate the data of the book
     * */
    @Autowired
    public BooksService(BooksRepository booksRepository ,Validator validator) {
        this.booksRepository = booksRepository;
        this.validator = validator;
    }

    /**this method is used to create a book
     * @param book this is the book that is going to be created
     * @return Book this is the book that was created
     * */
    @Override
    public Book createBook(BaseBookDTO book) {
        Set<ConstraintViolation<BaseBookDTO>> violations = validator.validate(book, OnCreate.class);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return createBookPvt(book);
    }
    /**this method is used to create a book
     * @param bookDTO this is the book that is going to be created
     * @return Book this is the book that was created
     * */
    private Book createBookPvt(BaseBookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setYear(bookDTO.getYear());
        book.setISBN(bookDTO.getISBN());
        booksRepository.save(book);
        return book;
    }

    /**this method is used to get all the books
     * @return List<Book> this is the list of all the books
     * */
    @Override
    public List<Book> getAllBooks() {
        return getAllBooksPvt();
    }
    /**this method is used to get all the books
     * @return List<Book> this is the list of all the books
     * */
    private List<Book> getAllBooksPvt() {
        return booksRepository.findAll();
    }

    /**this method is used to get a book
     * @param id this is the id of the book that is going to be returned
     * @return Book this is the book that was returned
     * */
    @Override
    public Book getBook(String id) {
        return booksRepository.findById(id).orElse(null);
    }
    /**this method is used to update a book
     * @param id this is the id of the book that is going to be updated
     * @param newBook this is the book that is going to be updated
     * @return Book this is the book that was updated
     * */
    @Override
    public Book updateBook(String id, BaseBookDTO newBook) throws UserNotExistException {
        Set<ConstraintViolation<BaseBookDTO>> violations = validator.validate(newBook, OnUpdateAll.class);
        if (!violations.isEmpty()) {
            throw new UserBadRequest( violations);
        }
        Book bookExist = booksRepository.findById(id).map(
                book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setDescription(newBook.getDescription());
                    book.setImage(newBook.getImage());
                    book.setAccess(newBook.getAccess());
                    book.setCategoryId(newBook.getCategoryId());
                    book.setTagsId(newBook.getTagsId());
                    book.setPublishedAt(newBook.getPublishedAt());
                    book.setYear(newBook.getYear());
                    book.setISBN(newBook.getISBN());
                    return book;
                }
        ).orElseThrow(() -> new UserNotExistException(404, "Book not found"));
        return booksRepository.save(bookExist);
    }
    /**this method is used to update a book
     * @param newBook this is the book that is going to be updated
     * @return Book this is the book that was updated
     * @throws UserNotExistException this is an exception that is thrown when the book does not exist
     * */
    @Override
    public Book updateBook(BaseBookDTO newBook) throws UserNotExistException {
        Book bookExist = booksRepository.findById(newBook.getIdBook()).map(
                book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setDescription(newBook.getDescription());
                    book.setYear(newBook.getYear());
                    book.setISBN(newBook.getISBN());
                    return book;
                }
                ).orElseThrow(() -> new UserNotExistException(404, "Book not found"));
        return booksRepository.save(bookExist);
    }


    @Override
    public String deleteBook(String id) throws UserNotExistException {
        Book bookExist = booksRepository.findById(id).orElseThrow(() -> new UserNotExistException(404, "Book not found"));
        booksRepository.deleteById(id);
        return "Book deleted";
    }

    /**this method is used to search books
     * @param title this is the title of the book that is going to be searched
     * @return List<Book> this is the list of books that were found
     * */
    @Override
    public List<Book> searchBooks(String title) {
        return (List<Book>) booksRepository.findByTitle(title);
    }
    /**this method is used to create a sample book
     * @return Book this is the sample book that was created
     * */
    public Book createSampleBook() {
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setISBN(123456);
        book.setYear(2020);
        book.setDescription("Description");

        return booksRepository.save(book);
    }

    public BooksRepository getBooksRepository() {
        return booksRepository;
    }

}
