package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.BranchOffice.BaseBranchOfficeDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.services.DTO.BranchOfficeDTO;
import jakarta.validation.ConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class BranchOfficeController  {

    @Autowired
    private BranchOfficeDTO branchService;
    private static final String BRANCH_OFFICE = "/api/v1/books-service";

    @GetMapping(BRANCH_OFFICE + "/BranchOffice")
    public ResponseEntity<?> GetBranchOffice(
            @RequestHeader(name = "Authorization", required = false) HeaderValidationDTO authorizationHeader
    ) {
        try {
            return ResponseEntity.ok(branchService.getOffices(authorizationHeader));
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(BRANCH_OFFICE + "/BranchOffice/Create")
    public ResponseEntity<?> CreateBranchOffice(
            @RequestHeader(name = "Authorization", required = false) HeaderValidationDTO authorizationHeader,
            @RequestBody BaseBranchOfficeDTO branch
    ){
        try {
            return ResponseEntity.ok(branchService.createOffice(branch, authorizationHeader ));
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping(BRANCH_OFFICE + "/BranchOffice/update/")
    public ResponseEntity<?> putBranchOffice (
            @RequestHeader(name = "Authorization", required = false) HeaderValidationDTO authorizationHeader,
            @RequestBody BaseBranchOfficeDTO branch
    ){
        try{
            branchService.updateOffice(authorizationHeader,branch);
            JSONObject response = new JSONObject();
            response.put("code", 200);
            response.put("message", "Branch Office updates susses");
            response.put("date", LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(BRANCH_OFFICE + "/BranchOffice/Deleted/{id}")
    public ResponseEntity<?> DeletedBranchOffice (
            @RequestHeader(name = "Authorization", required = false) HeaderValidationDTO authorizationHeader,
            @PathVariable String id
    ) {
        try {
            JSONObject response = new JSONObject();
            response.put("code", 200);
            response.put("message", branchService.delete(authorizationHeader, id));
            response.put("date", LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        }catch (UserNotExistException e){
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (ConstraintViolationException e){
        return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
