package com.csi.emphome.demo.repository.login;

import com.csi.emphome.demo.domain.user.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<UserItem,Integer> {
  UserItem findByusername(String username);
  UserItem findByLoginname(String loginname);
}
