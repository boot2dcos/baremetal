package io.boot2dcos.netboot.model.pixiecore.impl;

import io.boot2dcos.netboot.model.pixiecore.PixiecoreBootSpecification;
import lombok.*;

import java.util.*;

/**
 * Created by jauffreyflach on 07/04/2017.
 * A simple POJO that handles the ipxe boot script using Pixiecore specification.
 */
@Data
@NoArgsConstructor
public class PixiecoreBootSpecificationImpl implements PixiecoreBootSpecification {
    private String kernel;
    private final Collection<String> initrd = new ArrayList<>();
    private String cmdline;
    private String message;
}