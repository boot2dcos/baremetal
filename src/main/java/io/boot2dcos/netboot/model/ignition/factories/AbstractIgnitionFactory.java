package io.boot2dcos.netboot.model.ignition.factories;

import io.boot2dcos.netboot.config.NetbootConfiguration;
import io.boot2dcos.netboot.model.ignition.*;

import java.util.Arrays;

/**
 * Created by jauffreyflach on 12/06/2017.
 * netboot-http-api
 * <p>
 * Returns mutable objects that can be reused in sub-classes
 */
public abstract class AbstractIgnitionFactory implements IgnitionFactory {

    protected final NetbootConfiguration configuration;
    protected String ip;
    protected Boolean useProxy;

    public AbstractIgnitionFactory(NetbootConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Systemd makeSystemd() {
        final Systemd systemd = new Systemd();
        final SystemdUnit timesyncdUnit = SystemdUnit.builder().name("systemd-timesyncd.service").mask(Boolean.TRUE).enable(Boolean.FALSE).build();
        final SystemdUnit ntpdUnit = SystemdUnit.builder().name("ntpd.service").enable(Boolean.TRUE).build();
        if (useProxy) {
            final Dropin proxy = new Dropin("20-http-proxy.conf", "[Service]\nEnvironment=\"HTTP_PROXY=" + configuration.getHttpProxy()
                    + "\"\nEnvironment=\"HTTPS_PROXY=" + configuration.getHttpsProxy()
                    + "\"\nEnvironment=\"NO_PROXY=" + configuration.getNoProxy() + "\"");
            final SystemdUnit proxyUnit = SystemdUnit.builder().name("docker.service").enable(Boolean.TRUE).dropin(proxy).build();
            systemd.getUnits().add(proxyUnit);
        }
        systemd.getUnits().addAll(Arrays.asList(timesyncdUnit, ntpdUnit));
        return systemd;
    }

    @Override
    public Storage makeStorage() {

        final File dcosInstaller = File.builder().filesystem("root")
                .path("/dcos-installer")
                .contents(
                        new FileContents("http://" + configuration.getBoot2DCOSHostAddress() + ":81/v1/install/dcos_install.sh")
                ).build();

        final Storage storage = new Storage();
        storage.getFiles().addAll(Arrays.asList(dcosInstaller));
        return storage;
    }

    @Override
    public IgnitionSpecification getIgnitionSpecification(String ip) {
        this.ip = ip;
        this.useProxy = configuration.useProxy();
        final IgnitionSpecification.IgnitionSpecificationBuilder builder = IgnitionSpecification.builder()
                .systemd(makeSystemd())
                .storage(makeStorage())
                .networkd(makeNetworkd());

        return builder.build();
    }

    @Override
    public Networkd makeNetworkd() {
        return new Networkd();
    }
}
