package com.aomerge.rentbooks.services.DTO;

import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.branchOffice.BaseBranchOfficeDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.models.BranchOffice;

import java.util.List;

public interface BranchOfficeDTO {
    public BranchOffice createOffice (BaseBranchOfficeDTO Branch, HeaderValidationDTO headerValidation);
    public List<BranchOffice> getOffices (HeaderValidationDTO authorizationHeader);
    public void updateOffice ( HeaderValidationDTO authorizationHeader, BaseBranchOfficeDTO Offices) throws UserNotExistException;
    public String delete (HeaderValidationDTO authorizationHeader, String id) throws UserNotExistException;
}
