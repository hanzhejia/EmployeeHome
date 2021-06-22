package com.csi.emphome.demo.repository.dept;

import com.csi.emphome.demo.domain.dept.DeptItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptRepository extends JpaRepository<DeptItem,Integer>  {
    DeptItem findById(int id);
    List<DeptItem> findAllByNameLike(String name);
    DeptItem findTopByOrderByIdDesc();
    List<DeptItem> findAll(Specification spec);
}
