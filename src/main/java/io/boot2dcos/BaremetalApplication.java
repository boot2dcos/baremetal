package io.boot2dcos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BaremetalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaremetalApplication.class, args);
    }
}
