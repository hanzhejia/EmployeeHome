package com.csi.emphome.demo.rest.dept;

import com.csi.emphome.demo.service.dept.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/test")
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList() {
        return deptService.fetchListFunc();
    }
}
