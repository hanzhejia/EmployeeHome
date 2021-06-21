package com.csi.emphome.demo.service.employee.impl;
import com.csi.emphome.demo.domain.employee.EmployeeItem;
import com.csi.emphome.demo.repository.employee.EmployeeRepository;
import com.csi.emphome.demo.service.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = employeeRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String, Object> createListItemFunc(EmployeeItem data) {
//        testRepository.save(data);
        System.out.println(data);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }
}
