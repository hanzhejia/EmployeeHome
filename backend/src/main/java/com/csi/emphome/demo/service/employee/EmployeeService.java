package com.csi.emphome.demo.service.employee;
import com.csi.emphome.demo.domain.employee.EmployeeItem;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface EmployeeService {
    HashMap<String, Object> fetchListFunc();
    HashMap<String, Object> createListItemFunc(EmployeeItem data);
}
