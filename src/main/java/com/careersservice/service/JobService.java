package com.careersservice.service;

import com.careersservice.constant.AppConstants;
import com.careersservice.model.Employee;
import com.careersservice.model.Job;
import com.careersservice.model.JobSearch;
import com.careersservice.model.JobStatus;
import com.careersservice.repository.JobDtoRepository;
import com.careersservice.repository.JobRepository;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final IMap<String, Job> jobCache;
    private final JobRepository jobRepository;
    private final JobDtoRepository jobDtoRepository;
    private final WebClient.Builder webClientBuilder;

    public Mono<JobStatus> createJob(Job job) {
        log.info("In createJob(-)");
        return jobRepository.existsByJobId(job.getJobId())
                .flatMap(aBoolean -> {
                            if (Boolean.TRUE.equals(aBoolean)) {
                                log.info("Job already exists");
                                return Mono.just(
                                        new JobStatus(
                                                job.getJobId(),
                                                job.getJobName(),
                                                job.getJavaExperience(),
                                                job.getSpringExperience(),
                                                AppConstants.EMP_ALREADY_EXITS));
                            } else {
                                log.info("New Job create");
                                return jobRepository.save(job).
                                        map(j -> new JobStatus(
                                                job.getJobId(),
                                                job.getJobName(),
                                                job.getJavaExperience(),
                                                job.getSpringExperience(),
                                                AppConstants.EMP_CREATED));

                            }
                        }
                );
    }

    public Flux<Employee> findEmpForJobId(JobSearch jobSearch) {
        log.info("In findEmpForJobId(-)");
        return jobRepository.findByJobId(jobSearch.getJobId()).flatMap(job -> webClientBuilder.build()
                .post()
                .uri(AppConstants.EMPLOYEE_SERVICE_SKILL_SET_URI)
                .body(Mono.just(job.getJavaExperience()), JobStatus.class)
                .retrieve()
                .bodyToFlux(Employee.class));
    }

    public Mono<Job> getJobProfileFromCache(JobSearch jobSearch) {
        log.info("In getJobProfileFromCache(-)");
        Mono<Job> result = getJobFromCache(jobSearch.getJobId());
        return result
                .switchIfEmpty(getJobFromDB(jobSearch.getJobId()))
                .flatMap(this::saveJobToCache);
    }

    private Mono<Job> getJobFromCache(String id) {
        log.info("In getJobFromCache(-)");
        return Mono.fromCompletionStage(jobCache.getAsync(id));
    }

    private Mono<? extends Job> saveJobToCache(Job job) {
        log.info("In saveJobToCache(-)");
        jobCache.setAsync(job.getJobId(), job);
        return Mono.just(job);
    }

    private Mono<Job> getJobFromDB(String id) {
        log.info("In getJobFromDB(-)");
        return jobDtoRepository.findByJobId(id);
    }

}