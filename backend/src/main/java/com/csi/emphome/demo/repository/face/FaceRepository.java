package com.csi.emphome.demo.repository.face;

import com.csi.emphome.demo.domain.face.Face;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceRepository extends JpaRepository<Face,Integer> {
}
