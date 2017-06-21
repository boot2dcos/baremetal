package io.boot2dcos.netboot.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.*;

/**
 * Created by jauffreyflach on 16/05/2017.
 * boot2dcos-core
 */
@Component
@ConfigurationProperties
@Data
public class DCOSConfigurationProperties {

    private String bootstrapUrl;
    private String clusterName;
    private String exhibitorStorageBackend;
    private String ipDetectFilename;
    private String masterDiscovery;
    private final List<InetAddress> masterList = new ArrayList<>();
    private final List<InetAddress> resolvers = new ArrayList<>();
    private Boolean useProxy;
    private String httpProxy;
    private String httpsProxy;
    private List<String> noProxy = new ArrayList<>();

    public Integer getMasterCount() {
        return masterList.size();
    }
}
