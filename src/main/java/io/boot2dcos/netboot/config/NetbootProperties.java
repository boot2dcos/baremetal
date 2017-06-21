package io.boot2dcos.netboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jauffreyflach on 12/04/2017.
 * netboot-http-api
 */
@Component
@ConfigurationProperties(prefix = "pixiecore")
@Data
public class NetbootProperties {

    private final List<String> initrd = new ArrayList<>();
    private String kernel;
}
