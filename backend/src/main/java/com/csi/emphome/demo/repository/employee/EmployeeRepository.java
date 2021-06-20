package com.csi.emphome.demo.repository.employee;

import com.csi.emphome.demo.domain.employee.EmployeeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeItem,Integer> {
}
