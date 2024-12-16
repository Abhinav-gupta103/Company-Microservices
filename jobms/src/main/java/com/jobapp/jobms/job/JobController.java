package com.jobapp.jobms.job;

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

import com.jobapp.jobms.job.dto.JobDTO;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping()
    public Job addJob(@RequestBody Job job) {
        return this.jobService.addJob(job);
    }

    @GetMapping()
    public List<JobDTO> getJobs() {
        return this.jobService.getJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = this.jobService.getJobById(id);

        if (jobDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }

        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteJobById(@PathVariable Long id) {
        return this.jobService.deleteJobById(id);
    }

    @PutMapping("/{id}")
    public String updateJobById(@PathVariable Long id, @RequestBody Job updatedJob) {
        if (this.jobService.updateJobById(id, updatedJob)) {
            return "Job Updated";
        }
        return "Job Not Found";
    }
}
