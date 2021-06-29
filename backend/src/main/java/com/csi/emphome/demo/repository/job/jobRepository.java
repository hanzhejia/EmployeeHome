package com.csi.emphome.demo.repository.job;

import com.csi.emphome.demo.domain.job.job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface jobRepository extends JpaRepository<job,Integer>, JpaSpecificationExecutor<job> {
    job findById(int id);
   job findByNAME(String name);
    List<job> findAllByNAME(String info);
    //job findByNAME(String name);
}
