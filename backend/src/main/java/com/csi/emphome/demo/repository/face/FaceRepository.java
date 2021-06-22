package com.csi.emphome.demo.repository.face;

import com.csi.emphome.demo.domain.face.Face;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaceRepository extends JpaRepository<Face,Integer> {
    @Query(value = "INSERT INTO csi.facekey_inf VALUES ('?1', 'main')",nativeQuery = true)
    public List saveFaceById(String id);
}
