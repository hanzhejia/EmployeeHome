package com.csi.emphome.demo.rest.login;

import com.csi.emphome.demo.service.login.LoginService;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import com.csi.emphome.demo.service.login.dto.PwdTemp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/user")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService){this.loginService=loginService;}

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public HashMap<String, Object> login(@RequestBody LoginTemp data) {
        return loginService.createLoginItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/updatePwd")
    @ResponseBody
    public  HashMap<String, Object> updatePwd(@RequestBody PwdTemp data){
        System.out.println("pwddata:"+data);
        return loginService.updateLoginPwdFunc(data);
    }

    @CrossOrigin
    @GetMapping(value = "/info")
    @ResponseBody
    public HashMap<String, Object> info(@RequestParam("token") String data) {
        return loginService.getLoginInfoFunc(data);
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
