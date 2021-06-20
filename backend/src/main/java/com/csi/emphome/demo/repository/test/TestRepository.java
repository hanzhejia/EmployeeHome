package com.csi.emphome.demo.repository.test;

import com.csi.emphome.demo.domain.test.test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<test,Integer> {
}
