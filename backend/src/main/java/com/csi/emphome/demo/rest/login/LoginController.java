package com.csi.emphome.demo.rest.login;

import com.csi.emphome.demo.service.login.LoginService;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/user")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService){this.loginService=loginService;}
//    @CrossOrigin
//    @PostMapping(value = "/login")
//    @ResponseBody
//    public HashMap<String, Object> login() {
//        HashMap<String, Object> response = new HashMap<>();
//        HashMap<String, Object> responseData = new HashMap<>();
//        responseData.put("token",1);
//        response.put("code",20000);
//        response.put("msg","登录成功");
//        response.put("data",responseData);
//        return response;
//    }
@CrossOrigin
@PostMapping(value = "/login")
@ResponseBody
public HashMap<String, Object> login(@RequestBody LoginTemp data) {
  //  HashMap<String, Object> response = new HashMap<>();
  //  HashMap<String, Object> responseData = new HashMap<>();
  //  responseData.put("token",1);
  //  response.put("code",20000);
 //   response.put("msg","登录成功");
  //  response.put("data",responseData);
    return loginService.createLoginItemFunc(data);
}

    @CrossOrigin
    @GetMapping(value = "/info")
    @ResponseBody
    public HashMap<String, Object> info() {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("admin");
        responseData.put("roles",roles);
        responseData.put("name","Super admin");
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",20000);
        responseInfo.put("msg","登录成功");
        responseInfo.put("data",responseData);
        return responseInfo;
    }

    @CrossOrigin
    @PostMapping(value = "/logout")
    @ResponseBody
    public HashMap<String, Object> logout() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("token",1);
        response.put("code",20000);
        response.put("msg","登录成功");
        response.put("data",responseData);
        return response;
    }
}
