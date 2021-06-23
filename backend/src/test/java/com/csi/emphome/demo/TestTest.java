package com.csi.emphome.demo;

import com.csi.emphome.demo.rest.test.TestController;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class TestTest {
    private TestController testController;
    @Test
    void contextLoads() {
        TestListQuery data = new TestListQuery(1, 10);
        HashMap<String, Object> res = testController.fetchList(data);
        System.out.println(res);
    }
}
