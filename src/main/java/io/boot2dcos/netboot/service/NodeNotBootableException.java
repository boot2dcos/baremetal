package io.boot2dcos.netboot.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jauffreyflach on 16/05/2017.
 * netboot-http-api
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NodeNotBootableException extends RuntimeException {
}
