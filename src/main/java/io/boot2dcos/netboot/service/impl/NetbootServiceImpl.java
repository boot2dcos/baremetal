package io.boot2dcos.netboot.service.impl;

import io.boot2dcos.netboot.client.http.ClusterHttpClient;
import io.boot2dcos.netboot.client.model.Node;
import io.boot2dcos.netboot.config.*;
import io.boot2dcos.netboot.model.FactoryLocator;
import io.boot2dcos.netboot.model.ignition.IgnitionSpecification;
import io.boot2dcos.netboot.model.pixiecore.PixiecoreBootSpecification;
import io.boot2dcos.netboot.model.pixiecore.impl.*;
import io.boot2dcos.netboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jauffreyflach on 07/04/2017.
 * netboot-http-api
 */
@Service
public class NetbootServiceImpl implements NetbootService {

    private final NetbootConfiguration configuration;
    private final ClusterHttpClient coreClient;
    private final FactoryLocator locator;

    @Autowired
    public NetbootServiceImpl(NetbootConfiguration configuration, ClusterHttpClient coreClient, FactoryLocator locator) {
        this.configuration = configuration;
        this.coreClient = coreClient;
        this.locator = locator;
    }

    @Override
    public Boolean isRegistered(String macAdress) {
        return Boolean.FALSE;
    }

    @Override
    public PixiecoreBootSpecification getBootSpecification(String macAddress) throws NodeNotBootableException {
        // Check if the node is already registered.
        Node node;
        final String ignitionUrl = configuration.getIgnitionUrl() + macAddress;
        final CommandLineBuilder commandLineBuilder = new CommandLineBuilder(ignitionUrl);
        final String datacenter = configuration.getDataCenter();

        final Resource<Node> resource = coreClient.getNode(macAddress);
        if (resource == null) {

            // Create a new entry
            node = new Node();
            node.setMacAddress(macAddress);
            node.setDataCenter(datacenter);

            // If no master exists for this ip, provision
            if (configuration.getMasterCount() > coreClient.getRoleCount("master", datacenter)) {
                node.setRole("master");
                // Compare master list with ips in the cluster for a given DC
                Collection<Node> nodes = coreClient.getNodes(datacenter, "master").getContent();

                // Get a copy of the Master list
                List<InetAddress> ips = new ArrayList<>(configuration.getMasterList());
                if (nodes != null) {
                    final List<InetAddress> nodeIps =
                            nodes.stream()
                                    .filter(n -> n.getIp() != null)
                                    .map(n -> n.getIp())
                                    .collect(Collectors.toList());
                    // Clear IP list from existing ones
                    ips.removeAll(nodeIps);
                }
                node.setIp(ips.stream().findFirst().get());
            } else {
                // This is a slave node
                // TODO: public slave
                node.setRole("slave");
            }

            coreClient.addNode(node);
        } else {
            // Get the node from the request
            node = resource.getContent();
        }
        // Always use the set ip in case of reboot of a master
        if (node.getIp() != null) {
            commandLineBuilder.withIp(node.getIp().getHostAddress());
        }
        // TODO: Add a DEBUG switch
        commandLineBuilder.withRole(node.getRole()).withAutoLogin();

        // if the boot is done the first time, add the firstboot arg (maybe not needed... until we support persistent nodes)
        final PixiecoreBootSpecificationImpl bootspec = new PixiecoreBootSpecificationImpl();
        bootspec.setKernel(configuration.getKernel());
        bootspec.getInitrd().addAll(configuration.getInitrd());
        bootspec.setCmdline(commandLineBuilder.build());
        bootspec.setMessage("BOOT2DCOS");
        return bootspec;
    }

    @Override
    public IgnitionSpecification getIgnition(String macAddress, String role, String ip) {
        return locator.getIgnitionFactory(role + "IgnitionFactory").getIgnitionSpecification(ip);
    }
}
