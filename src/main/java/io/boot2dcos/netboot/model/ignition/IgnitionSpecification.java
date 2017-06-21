package io.boot2dcos.netboot.model.ignition;

import lombok.*;

/**
 * Created by jauffreyflach on 17/05/2017.
 * netboot-http-api
 */
@Data
@Builder
public class IgnitionSpecification {
    private final Ignition ignition = new Ignition();
    private Systemd systemd;
    private Networkd networkd;
    private Storage storage;
}
