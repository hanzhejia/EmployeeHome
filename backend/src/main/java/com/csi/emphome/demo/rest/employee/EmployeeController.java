package com.csi.emphome.demo.rest.employee;


import com.csi.emphome.demo.domain.employee.EmployeeItem;
import com.csi.emphome.demo.service.employee.EmployeeService;
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
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList() {
        return employeeService.fetchListFunc();
    }


    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody EmployeeItem data) {
        return employeeService.createListItemFunc(data);
    }
}
