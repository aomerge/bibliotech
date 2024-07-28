package com.aomerge.rentbooks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.aomerge.rentbooks.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<Book, String> {

    List<Book> findByTitle(String title);
}


