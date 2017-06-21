package io.boot2dcos.netboot.model.ignition;

import lombok.Data;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
public class FileContents {
    private final String source;
    // null or gzip
    private String compression;
    private  String verification;
}
