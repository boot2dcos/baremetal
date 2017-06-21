package io.boot2dcos.model.ignition;

import io.boot2dcos.netboot.config.NetbootConfiguration;
import io.boot2dcos.netboot.model.ignition.IgnitionSpecification;
import io.boot2dcos.netboot.model.ignition.factories.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.BDDMockito.given;

/**
 * Created by jauffreyflach on 10/06/2017.
 * netboot-http-api
 */
@RunWith(MockitoJUnitRunner.class)
public class MasterIgnitionFactoryTest {

    public static final String IP = "192.168.99.101";
    @Mock
    private NetbootConfiguration props = Mockito.mock(NetbootConfiguration.class);
    private IgnitionFactory factory;
    private IgnitionSpecification spec;


    @Before
    public void initSpec() throws UnknownHostException {
        given(props.getBoot2DCOSHostAddress()).willReturn("192.168.99.100");
        given(props.getGatewayHostAddress()).willReturn("192.168.99.1");
        given(props.getHttpProxy()).willReturn("http://toto");
        given(props.getHttpsProxy()).willReturn("http://toto");
        given(props.getNoProxy()).willReturn("*.boot2dcos");
        given(props.useProxy()).willReturn(Boolean.FALSE);
        factory = new MasterIgnitionFactory(props);
        spec = factory.getIgnitionSpecification(IP);
    }

    @Test
    public void httpProxyIsSet() {
        given(props.useProxy()).willReturn(Boolean.TRUE);
        factory = new MasterIgnitionFactory(props);
        spec = factory.getIgnitionSpecification(IP);
        assertThat(spec.getSystemd().getUnits()).flatExtracting(u -> u.getDropins()).extracting("name").contains("20-http-proxy.conf");
    }

    @Test
    public void httpProxyIsNotSet() {
        assertThat(spec.getSystemd().getUnits()).flatExtracting(u -> u.getDropins()).extracting("name").doesNotContain("20-http-proxy.conf");
    }

    @Test
    public void NTPConfigurationIsSet() {
        assertThat(spec.getSystemd().getUnits())
                .extracting("name", "mask", "enable")
                .contains(
                        tuple("systemd-timesyncd.service", Boolean.TRUE, Boolean.FALSE),
                        tuple("ntpd.service", Boolean.FALSE, Boolean.TRUE)
                );
    }

    @Test
    public void DCOSInstallerIsSet() {
        assertThat(spec.getStorage().getFiles()).extracting("path")
                .contains("/dcos-installer");
    }

    @Test
    public void staticIpIsSet() {
        assertThat(spec.getNetworkd().getUnits()).extracting("name").contains("00-eth0.network");
    }
}
