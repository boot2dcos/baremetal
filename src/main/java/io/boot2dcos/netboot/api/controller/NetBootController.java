package io.boot2dcos.netboot.api.controller;

import io.boot2dcos.netboot.model.ignition.IgnitionSpecification;
import io.boot2dcos.netboot.model.pixiecore.PixiecoreBootSpecification;
import io.boot2dcos.netboot.service.NetbootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jauffreyflach on 03/04/2017.
 * netboot-http-api
 */
@RestController
public class NetBootController {

    private final NetbootService netbootService;

    @Autowired
    public NetBootController(NetbootService netbootService) {
        this.netbootService = netbootService;
    }

    @RequestMapping(value = "/v1/boot/{macAddress}", method = RequestMethod.GET)
    public PixiecoreBootSpecification boot(@PathVariable String macAddress) {
        return netbootService.getBootSpecification(macAddress);
    }

    @RequestMapping(value = "/v1/ignition/{macAddress}", method = RequestMethod.GET)
    public IgnitionSpecification ignition(@PathVariable String macAddress, @RequestParam(value = "role") String role, @RequestParam(value = "ip", required = false) String ip) {

        return netbootService.getIgnition(macAddress, role, ip);
    }

}
