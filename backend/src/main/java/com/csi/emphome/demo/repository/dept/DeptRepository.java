package com.csi.emphome.demo.repository.dept;

import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<TestItem,Integer>  {
}
