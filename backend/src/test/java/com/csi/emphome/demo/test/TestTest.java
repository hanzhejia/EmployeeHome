package com.csi.emphome.demo.test;

import com.csi.emphome.demo.service.test.TestService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class TestTest {
    private final TestService testService;

    public TestTest(TestService testService) {
        this.testService = testService;
    }

    @Test
    void contextLoads() {
        TestListQuery data = new TestListQuery(1, 10);
        HashMap<String, Object> res = testService.fetchListFunc(data);
        System.out.println(res);
    }
}
