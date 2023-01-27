package com.careersservice.repository;

import com.careersservice.model.Job;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface JobDtoRepository extends ReactiveCassandraRepository<Job,String> {

    @AllowFiltering
    Mono<Job> findByJobId(String jobId);
}
