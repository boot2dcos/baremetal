package io.boot2dcos.netboot.model.ignition;

import lombok.*;

/**
 * Created by jauffreyflach on 22/05/2017.
 * netboot-http-api
 */
@Data
@Builder
public class File {

    private final String filesystem;
    private String path;
    private Integer mode;
    private FileContents contents;
}
