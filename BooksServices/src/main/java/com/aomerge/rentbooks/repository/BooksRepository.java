package com.aomerge.rentbooks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.aomerge.rentbooks.models.Book;

@Repository
public interface BooksRepository extends MongoRepository<Book, String> {
}


