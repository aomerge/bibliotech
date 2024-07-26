package com.aomerge.rentbooks.services;
import com.aomerge.rentbooks.services.DTO.BooksDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aomerge.rentbooks.repository.BooksRepository;
import com.aomerge.rentbooks.models.Book;

@Service
public class BooksService  implements BooksDTO {

    @Autowired
    private BooksRepository booksRepository;
    /*
    @PostConstruct
    public void init() {
        System.out.println("BooksRepository is " + (booksRepository == null ? "null" : "not null"));
    }
    */
    @Override
    public Book TestCreateBook() {
        return null;
    }

    @Override
    public String createBook(Book book) {
        return "";
    }

    public Book createSampleBook() {
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setISBN(123456);
        book.setYear(2020);
        book.setDescription("Description");

        booksRepository.save(book);
        return book;
    }
    public BooksRepository getBooksRepository() {
        return booksRepository;
    }
}
