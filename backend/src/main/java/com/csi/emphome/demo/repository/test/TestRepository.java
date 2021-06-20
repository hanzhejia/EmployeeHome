package com.csi.emphome.demo.repository.test;

import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestRepository extends JpaRepository<TestItem,Integer>, JpaSpecificationExecutor<TestItem> {
    TestItem findById(int id);
}
