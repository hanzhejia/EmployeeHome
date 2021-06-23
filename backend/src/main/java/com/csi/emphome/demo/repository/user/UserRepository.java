package com.csi.emphome.demo.repository.user;

import com.csi.emphome.demo.domain.user.UserItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<UserItem,Integer> , JpaSpecificationExecutor<UserItem> {
    UserItem findById(int id);
    List< UserItem> findAllByUsername(String name);
    UserItem findTopByOrderByIdDesc();
//    List< UserItem> findAll(Specification spec);
}
