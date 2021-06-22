package com.csi.emphome.demo.service.employee;
import com.csi.emphome.demo.service.employee.dto.EmployeeListQuery;
import com.csi.emphome.demo.service.employee.dto.EmployeeTemp;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface EmployeeService {
    HashMap<String, Object> fetchListFunc(EmployeeListQuery data);
    HashMap<String, Object> createListItemFunc(EmployeeTemp data);
    HashMap<String, Object> deleteListItemFunc(EmployeeTemp data);
    HashMap<String, Object> updateListItemFunc(EmployeeTemp data);
    HashMap<String, Object> searchListItemFunc(EmployeeTemp data,EmployeeListQuery query);
}
