package io.boot2dcos.netboot.model.ignition.factories;

import io.boot2dcos.netboot.config.NetbootConfiguration;
import io.boot2dcos.netboot.model.ignition.*;

/**
 * Created by jauffreyflach on 11/06/2017.
 * netboot-http-api
 */
public class SlaveIgnitionFactory extends AbstractIgnitionFactory {

    public SlaveIgnitionFactory(NetbootConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Systemd makeSystemd() {
        final Systemd systemd = super.makeSystemd();
        final SystemdUnit dcosInstallerUnit = SystemdUnit.builder().name("boot2dcos-installer.service").enable(Boolean.TRUE).contents("[Service]\nType=oneshot\nRemainAfterExit=yes\nExecStart=/usr/bin/bash /dcos-installer slave\n\n[Install]\nWantedBy=multi-user.target").build();
        systemd.getUnits().add(dcosInstallerUnit);
        return systemd;
    }
}
