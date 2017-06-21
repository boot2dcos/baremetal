package io.boot2dcos.netboot.model.ignition;

import lombok.*;

/**
 * Created by jauffreyflach on 04/06/2017.
 * netboot-http-api
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Dropin {

    private final String name;
    private String contents;
}
