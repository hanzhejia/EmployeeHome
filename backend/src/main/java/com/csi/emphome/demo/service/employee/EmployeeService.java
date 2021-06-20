package com.csi.emphome.demo.service.employee;

import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface EmployeeService {
    HashMap<String, Object> fetchListFunc();
}
