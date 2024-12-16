package com.jobapp.jobms.job;

import java.util.List;

import com.jobapp.jobms.job.dto.JobDTO;

public interface JobService {
    Job addJob(Job job);

    List<JobDTO> getJobs();

    JobDTO getJobById(Long id);

    String deleteJobById(Long id);

    Boolean updateJobById(Long id, Job updatedJob);
}
