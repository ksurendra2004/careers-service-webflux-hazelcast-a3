package com.careersservice.config;

import com.careersservice.constant.AppConstants;
import com.careersservice.model.Job;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<String, Job> jobCache(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap(AppConstants.JOB_CACHE);
    }
}
