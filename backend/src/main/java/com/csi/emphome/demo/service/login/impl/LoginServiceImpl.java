package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.login.Login;
import com.csi.emphome.demo.repository.login.LoginRepo;
import com.csi.emphome.demo.service.login.LoginService;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import com.csi.emphome.demo.service.login.dto.PwdTemp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
  //  private final TestRepository testRepository;
    private final LoginRepo loginRepo;
   // public LoginServiceImpl(TestRepository testRepository) {
       // this.testRepository = testRepository;
  //  }
    private static  HashMap<String, Object> response=new HashMap<>();
    private static HashMap<String, Object> responseData = new HashMap<>();
    private static ArrayList<String> roles = new ArrayList<>();
    public LoginServiceImpl(LoginRepo loginRepo){this.loginRepo=loginRepo;}
    @Override
    public HashMap<String, Object> createLoginItemFunc(LoginTemp data) {
        System.out.println("createLoginItemFunc:"+data);
        response.clear();
        responseData.clear();
        roles.clear();
//        HashMap<String, Object> response=new HashMap<>();
//        HashMap<String, Object> responseData = new HashMap<>();
//        ArrayList<String> roles = new ArrayList<>();
        Login loginTemp= loginRepo.findByusername(data.getUsername());
       if(data.getUsername().equals("admin")&&data.getPassword().equals(loginTemp.getPassword())){
            roles.add("admin");
            responseData.put("roles",roles);
            responseData.put("name",data.getUsername());
            responseData.put( "introduction","I am a super administrator");
            responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            response.put("code",20000);
            response.put("msg","success");
            response.put("data",responseData);
        }
       else if (data.getUsername().equals(loginTemp.getUsername())&&data.getPassword().equals(loginTemp.getPassword())){
           roles.add("editor");
           responseData.put("roles",roles);
           responseData.put("name",data.getUsername());
           responseData.put( "introduction","I am an editor");
           responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
           response.put("code",20000);
           response.put("msg","success");
           response.put("data",responseData);
       }
        else {
            response.put("code",60204);
        //    response.put("data",responseData);
         //   responseData.put("token",0);
            response.put("msg","Account and password are incorrect");
        }
        return response;
    }

    @Override
    public HashMap<String, Object> getLoginInfoFunc() {
        System.out.println("impl:getLoginInfoFunc");
        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data", responseData);
        return response;
    }


    @Override
    public HashMap<String, Object> updateLoginPwdFunc(PwdTemp data) {
        System.out.println("updateLoginPwdFunc"+data);
        Login login= loginRepo.findByusername(data.getUsername());
        login.setPassword(data.getNewpassword1());
        loginRepo.save(login);
        System.out.println(login);
        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    // @Override
   // public List<TestItem> list() {
      //  return testRepository.findAll();
  //  }
}
