package com.csi.emphome.demo.service.login;

import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface LoginService {
   // List<TestItem> list();
   HashMap<String, Object> createLoginItemFunc(LoginTemp data);
}
