package com.aomerge.rentbooks.services.DTO;

import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.category.BaseCaterogyDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.models.Book;
import com.aomerge.rentbooks.models.Category;

import java.util.List;

public interface CategoryDTO {

    public Category getCategory(String id) throws UserNotExistException;

    public List<Category> getAllCategory();

    public Category save (HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category);

    public void putBook(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) throws UserNotExistException;

    public void update (HeaderValidationDTO authorizationHeader,BaseCaterogyDTO category) throws UserNotExistException;

    public void deleted (HeaderValidationDTO authorizationHeader, String id) throws UserNotExistException;

    public List<Book> getCategoryBooks(String id) throws UserNotExistException ;

    public void deletedBook(HeaderValidationDTO authorizationHeader, BaseCaterogyDTO category) throws UserNotExistException;

}
