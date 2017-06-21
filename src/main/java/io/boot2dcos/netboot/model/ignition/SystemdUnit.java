package io.boot2dcos.netboot.model.ignition;

import lombok.*;

import java.util.*;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
@Builder
public class SystemdUnit {
    private final String name;
    @Singular
    private final List<Dropin> dropins;
    @Builder.Default
    private Boolean enable = Boolean.TRUE;
    private String contents;
    @Builder.Default
    private Boolean mask = Boolean.FALSE;
}
