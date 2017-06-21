package io.boot2dcos.netboot.service;

import io.boot2dcos.netboot.model.ignition.IgnitionSpecification;
import io.boot2dcos.netboot.model.pixiecore.PixiecoreBootSpecification;

/**
 * Created by jauffreyflach on 03/04/2017.
 *
 * This service provides a common interface to various external components.
 */
public interface NetbootService {

    /**
     * Checks if the given MAC Address is already registered in the cluster
     * @param macAdress a MAC address
     * @return true if the mac address is already registered
     */
    Boolean isRegistered(String macAdress);

    /**
     * Gets the Pixicore boot specification for a given mac address
     * @param macAddress a MAC address
     * @return a {@link PixiecoreBootSpecification} with specific parameters for the current boot
     */
    PixiecoreBootSpecification getBootSpecification(String macAddress) throws NodeNotBootableException;

    /**
     * Returns an Ignition configurationm depending on the role, mac address and IP
     *
     * @param macAddress
     * @param role
     * @param ip
     * @return
     */
    IgnitionSpecification getIgnition(String macAddress, String role, String ip);

}
