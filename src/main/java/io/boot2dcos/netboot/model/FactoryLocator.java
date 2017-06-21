package io.boot2dcos.netboot.model;

import io.boot2dcos.netboot.model.ignition.factories.IgnitionFactory;

/**
 * Created by jauffreyflach on 11/06/2017.
 * netboot-http-api
 */
public interface FactoryLocator {
    IgnitionFactory getIgnitionFactory(String role);

}
