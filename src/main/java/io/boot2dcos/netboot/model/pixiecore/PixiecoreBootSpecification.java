package io.boot2dcos.netboot.model.pixiecore;

import java.util.Collection;

/**
 * Created by jauffreyflach on 07/04/2017.
 * More information here: https://github.com/google/netboot/blob/master/pixiecore/README.api.md
 */
public interface PixiecoreBootSpecification {

    /**
     * URL of the kernel to boot
     * @return the kernel boot string
     */
    String getKernel();

    /**
     * (optional) URLs of initrds to load. The kernel will flatten all the initrds into a single filesystem.
     * @return URLs of initrds to load
     */
    Collection<String> getInitrd();

    /**
     * (optional) commandline parameters for the kernel.
     * The commandline is processed by Go's text/template library.
     * Within the template, a URL function is available that takes a URL and rewrites it such that Pixiecore proxies the request.
     * @return commandline parameters for the kernel
     */
    String getCmdline();

    /**
     * (optional) A message to display before booting the provided configuration.
     * Note that displaying this message is on a best-effort basis only, as particular implementations of the boot process may not
     * support displaying text.
     * @return A message to display before booting the provided configuration.
     */
    String getMessage();
}
