package com.companyapp.companyms.company;

import java.util.List;

public interface CompanyService {
    Company addCompany(Company company);

    List<Company> getAllCompanies();

    Boolean deleteCompanyById(Long id);

    Boolean updateCompanyById(Long id, Company updatedCompany);

    Company getCompanyById(Long id);
}
