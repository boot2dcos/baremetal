package io.boot2dcos.netboot.model.ignition.factories;

import io.boot2dcos.netboot.model.ignition.*;

/**
 * Created by jauffreyflach on 09/06/2017.
 * netboot-http-api
 */
public interface IgnitionFactory {
    IgnitionSpecification getIgnitionSpecification(String ip);

    Systemd makeSystemd();

    Networkd makeNetworkd();

    Storage makeStorage();
}
