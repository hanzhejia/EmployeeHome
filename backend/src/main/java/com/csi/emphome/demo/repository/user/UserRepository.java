package com.csi.emphome.demo.repository.user;
import com.csi.emphome.demo.domain.user.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserItem,Integer> , JpaSpecificationExecutor<UserItem> {

}
