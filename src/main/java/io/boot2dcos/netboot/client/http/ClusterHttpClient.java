package io.boot2dcos.netboot.client.http;

import io.boot2dcos.netboot.client.model.Node;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jauffreyflach on 05/05/2017.
 * netboot-http-api
 */
@FeignClient(value = "core", path = "v1/cluster", decode404 = true)
public interface ClusterHttpClient {

    @RequestMapping(method = RequestMethod.POST, value = "/nodes")
    void addNode(Node node);

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/search/findByMacAddress")
    Resource<Node> getNode(@RequestParam("macAddress") String macAddress);

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/search/countByRoleAndDataCenter")
    Integer getRoleCount(@RequestParam("role") String role, @RequestParam("dataCenter") String dataCenter);

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/search/findNodesByDataCenter")
    Resources<Node> getNodes(@RequestParam("dataCenter") String dataCenter);

    @RequestMapping(method = RequestMethod.GET, value = "/nodes/search/findNodesByDataCenterAndRole")
    Resources<Node> getNodes(@RequestParam("dataCenter") String dataCenter, @RequestParam("role") String role);

}
