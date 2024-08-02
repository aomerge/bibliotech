package com.aomerge.rentbooks.services;

import com.aomerge.rentbooks.config.exeptions.CustomAuthorizationException;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.BranchOffice.BaseBranchOfficeDTO;
import com.aomerge.rentbooks.config.validation.books.BaseBookDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.models.Book;
import com.aomerge.rentbooks.models.BranchOffice;
import com.aomerge.rentbooks.repository.BranchOfficeRepository;
import com.aomerge.rentbooks.services.DTO.BranchOfficeDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BranchOfficeService implements BranchOfficeDTO {

    @Autowired
    private final BranchOfficeRepository branchOfficesRespository;
    private final Validator validator;

    @Autowired
    public BranchOfficeService(BranchOfficeRepository branchOfficeRepository, Validator validator){
        this.branchOfficesRespository= branchOfficeRepository;
        this.validator = validator;
    }

    @Override
    public BranchOffice createOffice(BaseBranchOfficeDTO branch, HeaderValidationDTO headerValidation) {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(headerValidation);
        if (!violationHeader.isEmpty()){
            throw new ConstraintViolationException(violationHeader);
        }

        // validation Body
        Set<ConstraintViolation<BaseBranchOfficeDTO>> violations = validator.validate(branch, OnCreate.class);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        //Create Book
        BranchOffice branchOfficce = new BranchOffice();
        branchOfficce.setName(branch.getName());
        branchOfficce.setAddress(branch.getAddress());
        branchOfficce.setCity(branch.getCity());
        branchOfficce.setCountry(branch.getCountry());
        branchOfficce.setPhone(branch.getPhone());
        return branchOfficesRespository.save(branchOfficce);
    }

    @Override
    public List<BranchOffice> getOffices(HeaderValidationDTO headerValidation ) {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(headerValidation);
        if (!violationHeader.isEmpty()){
            throw new ConstraintViolationException(violationHeader);
        }

        return branchOfficesRespository.findAll();
    }

    @Override
    public void updateOffice( HeaderValidationDTO authorizationHeader, BaseBranchOfficeDTO Offices) {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new ConstraintViolationException(violationHeader);
        }

    }

    @Override
    public String delete(HeaderValidationDTO authorizationHeader, String id) throws UserNotExistException {
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new ConstraintViolationException(violationHeader);
        }
        BranchOffice bookExist = branchOfficesRespository.findById(id).orElseThrow(() -> new UserNotExistException(404, "Book not found"));
        branchOfficesRespository.deleteById(id);
        return "Book deleted";
    }
}
