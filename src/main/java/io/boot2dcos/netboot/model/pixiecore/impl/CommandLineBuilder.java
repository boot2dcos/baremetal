package io.boot2dcos.netboot.model.pixiecore.impl;

import java.util.StringJoiner;

/**
 * Created by jauffreyflach on 19/05/2017.
 * Builds the command line for PixiecoreBootSpecification
 */
public class CommandLineBuilder {
    // mandatory
    private final String ignitionURL;
    private final StringJoiner joiner = new StringJoiner(" ");
    private final StringJoiner urlJoiner = new StringJoiner("&");
    // <ip>::<gateway>:<netmask>:<hostname>:<iface>:none[:<dns1>[:<dns2>]]
    private String ip;
    // slave by default
    private String role = "slave";
    private String gateway;
    private String netmask;
    private String hostname;
    private String iface;
    private String dns1;
    private String dns2;


    public CommandLineBuilder(String ignitionURL){
        this.ignitionURL = ignitionURL;
    }

    public CommandLineBuilder withAutoLogin(){
        this.joiner.add("coreos.autologin");
        return this;
    }

    public CommandLineBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public CommandLineBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String build(){
        // Mandatory
        urlJoiner.add("role=" + role);
        // Optional in URL
        if (ip != null) urlJoiner.add("ip=" + ip);
        joiner.add("coreos.first_boot=1");
        joiner.add("coreos.config.url={{ URL \"" + ignitionURL + "?" + urlJoiner.toString() + "\" }}");
        return joiner.toString();

    }

}
