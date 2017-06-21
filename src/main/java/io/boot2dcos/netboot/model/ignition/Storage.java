package io.boot2dcos.netboot.model.ignition;

import lombok.*;

import java.util.*;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
@NoArgsConstructor
public class Storage {
    private final List<File> files = new ArrayList<>();
}
