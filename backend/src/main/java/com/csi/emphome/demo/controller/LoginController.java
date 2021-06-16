package com.csi.emphome.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class LoginController {
    @CrossOrigin
    @PostMapping(value = "/vue-admin-template/user/login")
    @ResponseBody
    public HashMap<String, Object> login() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("token",1);
        response.put("code",20000);
        response.put("msg","登录成功");
        response.put("data",responseData);
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/vue-admin-template/user/info")
    @ResponseBody
    public HashMap<String, Object> info() {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("roles","admin");
        responseData.put("name","Super admin");
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",20000);
        responseInfo.put("msg","登录成功");
        responseInfo.put("data",responseData);
        return responseInfo;
    }

    @CrossOrigin
    @PostMapping(value = "/vue-admin-template/user/logout")
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
