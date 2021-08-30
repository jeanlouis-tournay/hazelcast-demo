package com.cegeka.dplt.hazelcast.cache;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {

    private static final String CARS = "cars";


    private final HazelcastClusterConfig clusterConfig;
    private HazelcastInstance client;

    public CacheClient(HazelcastClusterConfig config) {
        this.clusterConfig =config;
        this.client=HazelcastClient.newHazelcastClient(creatClientConfig());
    }

    public Car put(String key, Car car) {
        IMap<String, Car> map = client.getMap(CARS);
        return map.putIfAbsent(key, car);
    }

    public Car get(String key) {
        IMap<String, Car> map = client.getMap(CARS);
        return map.get(key);
    }

    @Bean
    private ClientConfig creatClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig=new ClientNetworkConfig();
        if (clusterConfig.getAddresses() !=null) {
            String[] addresses= clusterConfig.getAddresses().split(",");
            networkConfig.addAddress(addresses);
        }
        clientConfig.setNetworkConfig(networkConfig);
        clientConfig.addNearCacheConfig(createNearCacheConfig());
        return clientConfig;
    }

    // Level 2 cache
    private NearCacheConfig createNearCacheConfig() {
        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setName(CARS);
        nearCacheConfig.setTimeToLiveSeconds(360);
        nearCacheConfig.setMaxIdleSeconds(60);
        return nearCacheConfig;
    }

}
