package io.boot2dcos.netboot.model.ignition;

import lombok.*;

import java.util.*;

/**
 * Created by jauffreyflach on 17/05/2017.
 * netboot-http-api
 */
@Data
@NoArgsConstructor
public class Systemd {
    private final List<SystemdUnit> units = new ArrayList<>();
}
