package io.boot2dcos.model;

import io.boot2dcos.netboot.model.pixiecore.impl.PixiecoreBootSpecificationImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jauffreyflach on 07/04/2017.
 * netboot-http-api
 */
@RunWith(SpringRunner.class)
@JsonTest
public class PixiecoreBootSpecificationJsonTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JacksonTester<PixiecoreBootSpecificationImpl> json;

    @Test
    public void testSerialization() throws IOException {
        PixiecoreBootSpecificationImpl spec = new PixiecoreBootSpecificationImpl();
        spec.setKernel("kernel");
        assertThat(this.json.write(spec)).hasJsonPathStringValue("@.kernel");
        assertThat(this.json.write(spec)).extractingJsonPathStringValue("@.kernel")
                .isEqualTo("kernel");
    }


    @Test
    public void testDeserialization() throws IOException {
        String content = "{\"kernel\":\"kernel\"}";
        assertThat(this.json.parseObject(content).getKernel()).isEqualTo("kernel");
    }


}
