package com.csi.emphome.demo.repository.face;

import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceRepository extends JpaRepository<TestItem,Integer> {
}
