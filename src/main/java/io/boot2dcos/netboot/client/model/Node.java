package io.boot2dcos.netboot.client.model;

import java.net.InetAddress;

/**
 * Created by jauffreyflach on 05/05/2017.
 * netboot-http-api
 */
public class Node {

    private String macAddress;
    private InetAddress ip;
    private String dataCenter;
    private String role;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
