package com.companyapp.companyms.company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CompanySeriveImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanySeriveImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company addCompany(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Boolean deleteCompanyById(Long id) {
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            this.companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            this.companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

}
