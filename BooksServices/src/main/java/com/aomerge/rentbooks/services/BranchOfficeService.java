package com.aomerge.rentbooks.services;

import com.aomerge.rentbooks.config.exeptions.CustomAuthorizationException;
import com.aomerge.rentbooks.config.exeptions.UserBadRequest;
import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.branchOffice.BaseBranchOfficeDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.config.validation.groups.OnCreate;
import com.aomerge.rentbooks.config.validation.groups.OnUpdate;
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
import java.util.function.Consumer;

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
            throw new CustomAuthorizationException(400, violationHeader);
        }

        // validation Body
        Set<ConstraintViolation<BaseBranchOfficeDTO>> violations = validator.validate(branch, OnCreate.class);
        if(!violations.isEmpty()) {
            throw new UserBadRequest(400, violations);
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
    public void updateOffice( HeaderValidationDTO authorizationHeader, BaseBranchOfficeDTO Offices) throws UserNotExistException {
        // validation Header
        Set<ConstraintViolation<HeaderValidationDTO>> violationHeader = validator.validate(authorizationHeader);
        if (!violationHeader.isEmpty()){
            throw new CustomAuthorizationException(400, violationHeader);
        }
        // validation Body
        Set<ConstraintViolation<BaseBranchOfficeDTO>> violations = validator.validate(Offices, OnUpdate.class);
        if(!violations.isEmpty()) {
            throw new UserBadRequest(400, violations);
        }

        BranchOffice branchOfficce = branchOfficesRespository.findById(Offices.getId()).map(
                office ->{
                    updateFieldIfNecessary(office::setName, Offices.getName(), office.getName());
                    updateFieldIfNecessary(office::setAddress, Offices.getAddress(), office.getAddress());
                    updateFieldIfNecessary(office::setCity, Offices.getCity(), office.getCity());
                    updateFieldIfNecessary(office::setCountry, Offices.getCountry(), office.getCountry());
                    updateFieldIfNecessary(office::setPhone, Offices.getPhone(), office.getPhone());
                    return office;
                })
                .orElseThrow(() -> new UserNotExistException(404, "Book not found"));

        branchOfficesRespository.save(branchOfficce);


    }
    private <T> void updateFieldIfNecessary(Consumer<T> setter, T newValue, T currentValue) {
        if (newValue != null && !newValue.equals(currentValue)) {
            setter.accept(newValue);
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
