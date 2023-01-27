package com.careersservice.repository;

import com.careersservice.model.Job;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface JobRepository extends ReactiveCassandraRepository<Job,String> {

    @AllowFiltering
    Flux<Job> findByJobId(String jobId);
    @AllowFiltering
    Mono<Boolean> existsByJobId(String jobId);
}
