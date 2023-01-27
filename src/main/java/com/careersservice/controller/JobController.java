package com.careersservice.controller;

import com.careersservice.model.Employee;
import com.careersservice.model.Job;
import com.careersservice.model.JobSearch;
import com.careersservice.model.JobStatus;
import com.careersservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/createJobProfile")
    public Mono<JobStatus> createJob(@RequestBody @Valid Job job){
        return this.jobService.createJob(job);

    }
    @PostMapping("/findEmpForJobID")
    public Flux<Employee> findEmpForJobId(@RequestBody JobSearch jobSearch){
        return this.jobService.findEmpForJobId(jobSearch);
    }

    @PostMapping("/getJobProfileFromCache")
    public Mono<Job> getJObProfileFromCache(@RequestBody JobSearch jobSearch){
        return this.jobService.getJobProfileFromCache(jobSearch);
    }


}
