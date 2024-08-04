package com.aomerge.rentbooks.Repository;

import com.aomerge.rentbooks.models.BooksInExisten;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookInExistenRepositry extends MongoRepository<BooksInExisten, String> {

    Optional<BooksInExisten> findByIdBook(String bookId);
}
