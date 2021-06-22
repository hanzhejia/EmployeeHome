package com.csi.emphome.demo.rest.employee;


import com.csi.emphome.demo.service.employee.EmployeeService;
import com.csi.emphome.demo.service.employee.dto.EmployeeListQuery;
import com.csi.emphome.demo.service.employee.dto.EmployeeTemp;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin
    @PostMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody EmployeeListQuery data) {
        return employeeService.fetchListFunc(data);
    }


    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody EmployeeTemp data) {
        return employeeService.createListItemFunc(data);
    }


    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody EmployeeTemp data) {
        return employeeService.deleteListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody EmployeeTemp data) {
        return employeeService.updateListItemFunc(data);
    }

    @CrossOrigin
    @GetMapping(value = "/search")
    @ResponseBody
    public HashMap<String, Object> searchListItem( EmployeeTemp data, EmployeeListQuery query) {
        return employeeService.searchListItemFunc(data,query);
    }
}
