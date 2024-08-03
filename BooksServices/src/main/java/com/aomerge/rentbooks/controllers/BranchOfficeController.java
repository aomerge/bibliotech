package com.aomerge.rentbooks.controllers;

import com.aomerge.rentbooks.config.exeptions.UserNotExistException;
import com.aomerge.rentbooks.config.validation.branchOffice.BaseBranchOfficeDTO;
import com.aomerge.rentbooks.config.validation.global.HeaderValidationDTO;
import com.aomerge.rentbooks.services.DTO.BranchOfficeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get all Branch Office")
    @ApiResponse(responseCode = "200", description = "Return all Branch Office")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping(BRANCH_OFFICE + "/BranchOffice")
    public ResponseEntity<?> GetBranchOffice(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader
    ) {
        try {
            // validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            return ResponseEntity.ok(branchService.getOffices(headerValidationDTO));
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Create a Branch Office")
    @ApiResponse(responseCode = "200", description = "Return Branch Office created")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping(BRANCH_OFFICE + "/BranchOffice/Create")
    public ResponseEntity<?> CreateBranchOffice(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseBranchOfficeDTO branch
    ){
        try {
            // validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // service
            return ResponseEntity.ok(branchService.createOffice(branch, headerValidationDTO ));
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Update a Branch Office")
    @ApiResponse(responseCode = "200", description = "Return Branch Office updated")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping(BRANCH_OFFICE + "/BranchOffice/update/")
    public ResponseEntity<?> putBranchOffice (
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @RequestBody BaseBranchOfficeDTO branch
    ){
        try{
            // validation Header
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);
            // validation Body
            branchService.updateOffice(headerValidationDTO,branch);
            JSONObject response = new JSONObject();
            response.put("code", 200);
            response.put("message", "Branch Office updates susses");
            response.put("date", LocalDateTime.now().toString());
            return ResponseEntity.ok(response.toString());
        }catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a Branch Office")
    @ApiResponse(responseCode = "200", description = "Return Branch Office deleted")
    @ApiResponse(responseCode = "400", description = "Error in the request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping(BRANCH_OFFICE + "/BranchOffice/Deleted/{id}")
    public ResponseEntity<?> DeletedBranchOffice (
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader,
            @PathVariable String id
    ) {
        try {
            HeaderValidationDTO headerValidationDTO = new HeaderValidationDTO();
            headerValidationDTO.setAuthorizationHeader(authorizationHeader);

            JSONObject response = new JSONObject();
            response.put("code", 200);
            response.put("message", branchService.delete(headerValidationDTO, id));
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
