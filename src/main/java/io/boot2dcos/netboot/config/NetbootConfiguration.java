package io.boot2dcos.netboot.config;

import io.boot2dcos.netboot.model.FactoryLocator;
import io.boot2dcos.netboot.model.ignition.factories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.*;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jauffreyflach on 19/04/2017.
 * netboot-http-api
 */
@Configuration
public class NetbootConfiguration {

    // Configuration properties
    @Autowired
    private DCOSConfigurationProperties dcosProperties;

    @Autowired
    private Boot2DCOSProperties boot2DCOSProperties;

    @Autowired
    private NetbootProperties netbootProperties;

    @Bean
    public ServiceLocatorFactoryBean factoryLocator(){
        ServiceLocatorFactoryBean slfb = new ServiceLocatorFactoryBean();
        slfb.setServiceLocatorInterface(FactoryLocator.class);
        return slfb;
    }

    @Bean("masterIgnitionFactory")
    public IgnitionFactory masterIgnitionFactory(){
        return new MasterIgnitionFactory(this);
    }

    @Bean("slaveIgnitionFactory")
    public IgnitionFactory slavegnitionFactory(){
        return new SlaveIgnitionFactory(this);
    }

    public String getIgnitionUrl(){
        return "http://" + boot2DCOSProperties.getHost().getHostAddress() + ":81/v1/ignition/";
    }

    public String getDataCenter(){
        return boot2DCOSProperties.getDataCenter();
    }

    public Integer getMasterCount(){
        return dcosProperties.getMasterCount();
    }


    public List<InetAddress> getMasterList() {
        return dcosProperties.getMasterList();
    }

    public String getKernel(){
        return netbootProperties.getKernel();
    }

    public List<String> getInitrd(){
        return netbootProperties.getInitrd();
    }

    public String getBoot2DCOSHostAddress(){
        return boot2DCOSProperties.getHost().getHostAddress();
    }

    public Boolean useProxy(){
        return dcosProperties.getUseProxy();
    }

    public String getGatewayHostAddress(){
        return boot2DCOSProperties.getGateway().getHostAddress();
    }

    public String getHttpProxy(){
        return dcosProperties.getHttpProxy();
    }

    public String getHttpsProxy(){
        return dcosProperties.getHttpsProxy();
    }

    public String getNoProxy(){
        return dcosProperties.getNoProxy().stream().collect(Collectors.joining(","));
    }
}
