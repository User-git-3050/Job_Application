package com.example.msjob.controller;

import com.example.msjob.dao.request.CompanyRequest;
import com.example.msjob.dao.response.CompanyResponse;
import com.example.msjob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company/")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("name/{companyName}")
    public ResponseEntity<CompanyResponse> getCompanyByName(@PathVariable String companyName) {
        return ResponseEntity.ok(companyService.getCompanyByName(companyName));
    }

    @PostMapping()
    public ResponseEntity<String> createCompany(@RequestBody CompanyRequest companyRequest,@RequestParam Long ownerId){
        return ResponseEntity.ok(companyService.createCompany(companyRequest,ownerId));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest,@RequestHeader String username){
        return ResponseEntity.ok(companyService.updateCompanyById(id,companyRequest,username));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id,@RequestHeader String username) {
        return ResponseEntity.ok(companyService.deleteCompanyById(id,username));
    }

}
