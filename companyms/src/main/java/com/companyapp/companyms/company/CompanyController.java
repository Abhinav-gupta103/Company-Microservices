package com.companyapp.companyms.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanySeriveImpl companyService;

    public CompanyController(CompanySeriveImpl companySerice) {
        this.companyService = companySerice;
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return this.companyService.addCompany(company);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return this.companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = this.companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        if (this.companyService.deleteCompanyById(id)) {
            return new ResponseEntity<String>("Company Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Company Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public String updateCompanyById(@PathVariable Long id, @RequestBody Company updatedCompany) {
        if (this.companyService.updateCompanyById(id, updatedCompany))
            return "Company Updated";
        return "Company Not Found";
    }

}
