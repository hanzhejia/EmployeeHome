package com.csi.emphome.demo.rest.test;

import com.csi.emphome.demo.service.test.TestService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestSearchData;
import com.csi.emphome.demo.service.test.dto.TestTemp;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @PostMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody TestListQuery data) {
        return testService.fetchListFunc(data);
    }
    @CrossOrigin
    @PostMapping(value = "/listItem")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody TestSearchData data) {
        return testService.fetchListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody TestTemp data) {
        return testService.createListItemFunc(data);
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody TestTemp data) {
        return testService.updateListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody TestTemp data) {
        return testService.deleteListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/apiTestNormal")
    @ResponseBody
    public HashMap<String, Object> apiTestNormal() {
        return testService.apiTestNormalFunc();
    }

    @RequiresPermissions("user:admin")
    @CrossOrigin
    @PostMapping(value = "/apiTestAdmin")
    @ResponseBody
    public HashMap<String, Object> apiTestAdmin() {
        return testService.apiTestAdminFunc();
    }
}
