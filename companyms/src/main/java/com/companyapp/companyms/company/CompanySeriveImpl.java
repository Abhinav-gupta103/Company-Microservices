package com.companyapp.companyms.company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.companyapp.companyms.company.clients.ReviewClient;
import com.companyapp.companyms.company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanySeriveImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanySeriveImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
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

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company Not Found Exception" + reviewMessage.getCompanyId()));
        Double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        System.out.println(averageRating);
        company.setRating(averageRating);
        companyRepository.save(company);
    }

}
