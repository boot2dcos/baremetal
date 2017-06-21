package io.boot2dcos.netboot.model.ignition;

import lombok.*;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
@Builder
public class NetworkdUnit {
    private final String name;
    private String contents;

}
