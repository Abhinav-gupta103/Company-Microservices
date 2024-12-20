package com.jobapp.jobms.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobapp.jobms.job.clients.CompanyClient;
import com.jobapp.jobms.job.clients.ReviewClient;
import com.jobapp.jobms.job.dto.JobDTO;
import com.jobapp.jobms.job.external.Company;
import com.jobapp.jobms.job.external.Review;
import com.jobapp.jobms.job.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;
    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    int attempt = 0;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public Job addJob(Job job) {
        return this.jobRepository.save(job);
    }

    private JobDTO convertJobDTO(Job job) {
        // RestTemplate restTemplate = new RestTemplate();
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = this.reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapToJobDTO(job, company, reviews);
    }

    @Override
    // @CircuitBreaker(name = "companyBreaker", fallbackMethod =
    // "companyBreakerFallback")
    // @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getJobs() {
        System.out.println("attempt: " + ++attempt);
        List<Job> jobs = this.jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(this.convertJobDTO(job));
        }
        return jobs.stream()
                .map(this::convertJobDTO)
                .collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = this.jobRepository.findById(id).orElse(null);
        return this.convertJobDTO(job);
    }

    @Override
    public String deleteJobById(Long id) {
        Optional<Job> job = this.jobRepository.findById(id);
        if (job.isPresent()) {
            this.jobRepository.deleteById(id);
            return "Job Deleted";
        }
        return "Job Not Found";
    }

    @Override
    public Boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(job.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            this.jobRepository.save(job);
            return true;
        }
        return false;
    }
}
