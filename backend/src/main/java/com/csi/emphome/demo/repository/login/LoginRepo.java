package com.csi.emphome.demo.repository.login;

import com.csi.emphome.demo.domain.login.Login;
import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login,Integer> {
  //  Optional<Login> findByUsername(String username);
    Login findByusername(String username);
    Login findByPassword(String password);
}
