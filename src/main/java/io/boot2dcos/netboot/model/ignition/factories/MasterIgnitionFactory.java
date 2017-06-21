package io.boot2dcos.netboot.model.ignition.factories;

import io.boot2dcos.netboot.config.*;
import io.boot2dcos.netboot.model.ignition.*;

/**
 * Created by jauffreyflach on 09/06/2017.
 * netboot-http-api
 */
public class MasterIgnitionFactory extends AbstractIgnitionFactory {


    public MasterIgnitionFactory(NetbootConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Systemd makeSystemd() {
        final Systemd systemd = super.makeSystemd();
        final SystemdUnit dcosInstallerUnit = SystemdUnit.builder().name("boot2dcos-installer.service").enable(Boolean.TRUE).contents("[Service]\nType=oneshot\nRemainAfterExit=yes\nExecStart=/usr/bin/bash /dcos-installer master\n\n[Install]\nWantedBy=multi-user.target").build();
        systemd.getUnits().add(dcosInstallerUnit);
        return systemd;
    }

    @Override
    public Networkd makeNetworkd() {
        final Networkd networkd = super.makeNetworkd();
        final String gateway = !configuration.useProxy()?"\nGateway=" + configuration.getGatewayHostAddress():"";
        final NetworkdUnit staticIp = NetworkdUnit.builder().name("00-eth0.network").contents(
                "[Match]\nName=en*\n\n[Network]\nAddress=" + ip + gateway).build();
        networkd.getUnits().add(staticIp);
        return networkd;
    }
}
