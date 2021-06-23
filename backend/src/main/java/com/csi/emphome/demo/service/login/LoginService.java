package com.csi.emphome.demo.service.login;

import com.csi.emphome.demo.service.login.dto.LoginTemp;
import com.csi.emphome.demo.service.login.dto.PwdTemp;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface LoginService {
   HashMap<String, Object> createLoginItemFunc(LoginTemp data);
   HashMap<String, Object> getLoginInfoFunc(String data);
   HashMap<String, Object> updateLoginPwdFunc(PwdTemp data);
}
