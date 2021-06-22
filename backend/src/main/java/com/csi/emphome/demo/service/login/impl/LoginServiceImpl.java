package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.repository.login.LoginRepo;
import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.login.LoginService;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
  //  private final TestRepository testRepository;
    private final LoginRepo loginRepo;
   // public LoginServiceImpl(TestRepository testRepository) {
       // this.testRepository = testRepository;
  //  }
    public LoginServiceImpl(LoginRepo loginRepo){this.loginRepo=loginRepo;}
    @Override
    public HashMap<String, Object> createLoginItemFunc(LoginTemp data) {
        System.out.println("createLoginItemFunc:"+data);
        HashMap<String, Object> response=new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        if (data.getUsername().equals("admin")&&data.getPassword().equals("111111")){
            response.put("code",20000);
            response.put("data",responseData);
            responseData.put("token",1);
            response.put("msg","登录成功");
        }
        else {
            response.put("code",50000);
            response.put("data",responseData);
            responseData.put("token",0);
            response.put("msg","登录失败");
        }

        return response;
    }

    // @Override
   // public List<TestItem> list() {
      //  return testRepository.findAll();
  //  }
}
