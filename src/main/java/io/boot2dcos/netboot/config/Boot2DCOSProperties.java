package io.boot2dcos.netboot.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by jauffreyflach on 04/06/2017.
 * netboot-http-api
 */
@Component
@ConfigurationProperties(prefix = "boot2dcos")
@Data
public class Boot2DCOSProperties {

    private InetAddress host;
    private String dataCenter;
    private InetAddress gateway;
}
