package com.cegeka.dplt.hazelcast.cache;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("hzcluster")
@Data
public class HazelcastClusterConfig
{
    private String addresses;
}
