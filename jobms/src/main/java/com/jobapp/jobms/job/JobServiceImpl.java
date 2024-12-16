package com.jobapp.jobms.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobapp.jobms.job.dto.JobDTO;
import com.jobapp.jobms.job.external.Company;
import com.jobapp.jobms.job.external.Review;
import com.jobapp.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job addJob(Job job) {
        return this.jobRepository.save(job);
    }

    private JobDTO convertJobDTO(Job job) {

        // RestTemplate restTemplate = new RestTemplate();

        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/company/" + job.getCompanyId(),
                Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = reviewResponse.getBody();
        return JobMapper.mapToJobDTO(job, company, reviews);
    }

    @Override
    public List<JobDTO> getJobs() {
        List<Job> jobs = this.jobRepository.findAll();
        List<JobDTO> jobDTOs = new ArrayList<>();
        for (Job job : jobs) {
            jobDTOs.add(this.convertJobDTO(job));
        }
        return jobs.stream()
                .map(this::convertJobDTO)
                .collect(Collectors.toList());
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
