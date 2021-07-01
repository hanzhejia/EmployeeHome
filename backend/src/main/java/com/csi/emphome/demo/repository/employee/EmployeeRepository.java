package com.csi.emphome.demo.repository.employee;

import com.csi.emphome.demo.domain.employee.EmployeeItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<EmployeeItem,Integer>, JpaSpecificationExecutor<EmployeeItem> {
    EmployeeItem findBycard(String card);
    EmployeeItem findByid(int id);
    Page<EmployeeItem> findAll(Specification<EmployeeItem> specification, Pageable var2);   //传入Specification对象
    List<EmployeeItem> findAllBydeptid(int deptid);
}
