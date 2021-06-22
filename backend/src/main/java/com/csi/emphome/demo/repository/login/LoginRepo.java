package com.csi.emphome.demo.repository.login;

import com.csi.emphome.demo.domain.login.Login;
import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<TestItem,Integer> {
  //  Optional<Login> findByUsername(String username);
}
