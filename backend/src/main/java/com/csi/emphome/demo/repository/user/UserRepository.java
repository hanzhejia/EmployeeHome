package com.csi.emphome.demo.repository.user;

import com.csi.emphome.demo.domain.notice.noti;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<noti,Integer> {
}
