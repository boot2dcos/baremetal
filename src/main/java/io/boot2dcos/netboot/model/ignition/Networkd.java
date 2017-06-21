package io.boot2dcos.netboot.model.ignition;

import lombok.*;

import java.util.*;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
@NoArgsConstructor
public class Networkd {
    private final List<NetworkdUnit> units = new ArrayList<>();
}
