package com.csi.emphome.demo.rest.test;


import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.service.test.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList() {
        return testService.fetchListFunc();
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody TestItem data) {
        return testService.createListItemFunc(data);
    }
}
