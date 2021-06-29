package com.csi.emphome.demo.rest.dept;

import com.csi.emphome.demo.service.dept.DeptService;
import com.csi.emphome.demo.service.dept.dto.DeptListQuery;
import com.csi.emphome.demo.service.dept.dto.DeptSearchData;
import com.csi.emphome.demo.service.dept.dto.DeptTemp;
import com.csi.emphome.demo.service.test.dto.TestSearchData;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/vue-admin-template/department")
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @CrossOrigin
    @PostMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody DeptListQuery data) {
        return deptService.fetchListFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/deptlist")
    @ResponseBody
    public HashMap<String, Object> fetchDeptList() {
        return deptService.fetchDeptList();
    }

    @CrossOrigin
    @PostMapping(value = "/listItem")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody DeptSearchData data) {
        return deptService.fetchListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody DeptTemp data) {
        System.out.println(data);
        return deptService.createListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody List<DeptTemp> data) {
        return deptService.deleteListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody DeptTemp data) {
        return deptService.updateListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/check")
    @ResponseBody
    public HashMap<String, Object> checkSameName(@RequestBody DeptTemp data) {
        return deptService.checkSameNameFunc(data);
    }
}
