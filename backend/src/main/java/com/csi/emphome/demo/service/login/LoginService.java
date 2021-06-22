package com.csi.emphome.demo.service.login;

import com.csi.emphome.demo.service.login.dto.LoginTemp;
import com.csi.emphome.demo.service.login.dto.PwdTemp;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface LoginService {
   // List<TestItem> list();
   HashMap<String, Object> createLoginItemFunc(LoginTemp data);
   HashMap<String, Object> getLoginInfoFunc();
   HashMap<String, Object> updateLoginPwdFunc(PwdTemp data);
}
