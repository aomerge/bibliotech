package com.aomerge.rentbooks.repository;

import com.aomerge.rentbooks.models.BranchOffice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOfficeRepository extends MongoRepository<BranchOffice, String> {
}
