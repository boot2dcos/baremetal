package io.boot2dcos.netboot.api;

import io.boot2dcos.netboot.model.pixiecore.impl.PixiecoreBootSpecificationImpl;
import io.boot2dcos.netboot.service.NetbootService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jauffreyflach on 03/04/2017.
 * netboot-http-api
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NetBootControllerTest {

    private static final String KERNEL= "kernel";

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private NetbootService mockNetbootService;


    /**
     * Tests that the API answers to the right pixiecore endpoint (GET <apiserver-prefix>/v1/boot/<mac-addr>)
     * with all mandatory parameters.
     */
    @Test
    public void testCallWithMacAddressReturnsJSON() throws Exception {
        // GIVEN a predifined answer of the cluster status service
        // a MAC address, mock the service response
        final String macAddress = "08:00:27:57:0c:58";

        final PixiecoreBootSpecificationImpl pixiecoreBootSpecification = new PixiecoreBootSpecificationImpl();
        pixiecoreBootSpecification.setKernel(KERNEL);
        given(this.mockNetbootService.getBootSpecification(macAddress)).willReturn(pixiecoreBootSpecification);
        // WHEN the API endpoint is called
        // THEN we should have a 200 OK + JSON file response
        mockmvc.perform(get("/v1/boot/" + macAddress))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.kernel").value(KERNEL));
    }

}
