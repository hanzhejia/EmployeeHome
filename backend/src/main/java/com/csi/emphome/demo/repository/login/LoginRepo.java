package com.csi.emphome.demo.repository.login;

import com.csi.emphome.demo.domain.test.test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<test,Integer> {

}
