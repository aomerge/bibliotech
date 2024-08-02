package com.aomerge.rentbooks.services.DTO;

import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.books.BaseBookDTO;
import com.aomerge.rentbooks.models.Book;

import java.util.List;

/**this is the interface of the books
 * 1- createSampleBook
 * 2- createBook
 * 3- getAllBooks
 * 4- getBook
 * 5- updateBook
 * 6- deleteBook
 * 7- searchBooks
 * */
public interface BooksDTO {
    /**this method is used to create a sample book
     * @return Book this is the book that was created
     * */
    public Book createSampleBook();
    /**this method is used to create a book
     * @param book this is the book that is going to be created
     * @return Book this is the book that was created
     * */
    public Book createBook(BaseBookDTO book, String authorizationHeader);
    /**this method is used to get all the books
     * @return List<Book> this is the list of all the books
     * */
    public List<Book> getAllBooks();
    /**this method is used to get a book
     * @param id this is the id of the book that is going to be get
     * @return Book this is the book that was get
     * */
    public Book getBook(String id);
    /**this method is used to update a book
     * @param id this is the id of the book that is going to be updated
     * @param book this is the book that is going to be updated
     * @return Book this is the book that was updated
     * */
    public Book updateBook(String id, BaseBookDTO book) throws UserNotExistException;
    /**this method is used to update a book
     * @param book this is the book that is going to be updated
     * @return Book this is the book that was updated
     * */
    public Book updateBook(BaseBookDTO book) throws UserNotExistException;
    /**this method is used to delete a book
     * @param id this is the id of the book that is going to be deleted
     * */
    public String deleteBook(String id) throws UserNotExistException;

    /**this method is used to search books
     * @param title this is the title of the book that is going to be searched
     * @return List<Book> this is the list of books that were found
     * */
    public List<Book> searchBooks(String title);

}
