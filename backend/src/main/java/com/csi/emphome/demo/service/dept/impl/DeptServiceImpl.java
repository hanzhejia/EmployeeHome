package com.csi.emphome.demo.service.dept.impl;

import com.csi.emphome.demo.repository.dept.DeptRepository;
import com.csi.emphome.demo.service.dept.DeptService;

import java.util.HashMap;
import java.util.List;

public class DeptServiceImpl implements DeptService {
    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = deptRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }
}
