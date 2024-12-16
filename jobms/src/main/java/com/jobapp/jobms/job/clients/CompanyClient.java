package com.jobapp.jobms.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobapp.jobms.job.external.Company;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
