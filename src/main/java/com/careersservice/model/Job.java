package com.careersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "job")
public class Job implements Serializable {

    @NotNull(message = "The job id must not be null")
    @PrimaryKeyColumn(name = "job_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String jobId;
    @NotNull(message = "The job name must not be null")
    @NotBlank(message = "The job name must not be blank")
    @Column("job_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String jobName;
    @NotNull(message = "The java experience must not be null")
    @PrimaryKeyColumn(name = "java_exp", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private double javaExperience;
    @NotNull(message = "The spring experience must not be null")
    @PrimaryKeyColumn(name = "spring_exp", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private double springExperience;
}
