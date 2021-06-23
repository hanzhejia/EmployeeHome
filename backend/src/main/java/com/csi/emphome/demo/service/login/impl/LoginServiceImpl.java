package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.login.Login;
import com.csi.emphome.demo.jwt.JwtUtil;
import com.csi.emphome.demo.repository.login.LoginRepo;
import com.csi.emphome.demo.service.login.LoginService;
import com.csi.emphome.demo.service.login.dto.LoginTemp;
import com.csi.emphome.demo.service.login.dto.PwdTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${custom.jwt.expire_time}")
    private long expireTime;
    private final LoginRepo loginRepo;
    private static final HashMap<String, Object> response=new HashMap<>();
    public LoginServiceImpl(LoginRepo loginRepo){this.loginRepo=loginRepo;}

    @Override
    public HashMap<String, Object> createLoginItemFunc(LoginTemp data) {
        Login loginTemp= loginRepo.findByusername(data.getUsername());
        if(loginTemp!=null){
            if(loginTemp.getUsername().equals(data.getUsername()) && loginTemp.getPassword().equals(data.getPassword())){
                HashMap<String, Object> token = new HashMap<>();
                String token_str = JwtUtil.sign(loginTemp.getUsername(), loginTemp.getPassword());
                redisTemplate.opsForValue().set(token_str,token_str, expireTime*2/100, TimeUnit.SECONDS);
                token.put("token", token_str);
                response.put("code",20000);
                response.put("data",token);
                return response;
            }
        }
        response.put("code",60204);
        response.put("message","Account and password are incorrect.");
        return response;
    }

    @Override
    public HashMap<String, Object> getLoginInfoFunc(String data) {
        String username = JwtUtil.getUsername(data);

        HashMap<String, Object> user = new HashMap<>();
        HashMap<String, Object> user_admin = new HashMap<>();
        ArrayList<String> roles_admin = new ArrayList<>();
        roles_admin.add("admin");
        user_admin.put("roles", roles_admin);
        user_admin.put("introduction", "I am a super administrator");
        user_admin.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user_admin.put("name", "Super Admin");
        user.put("admin", user_admin);
        HashMap<String, Object> user_editor = new HashMap<>();
        ArrayList<String> roles_editor = new ArrayList<>();
        roles_editor.add("editor");
        user_editor.put("roles", roles_editor);
        user_editor.put("introduction", "I am an editor");
        user_editor.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user_editor.put("name", "Normal Editor");
        user.put("editor", user_editor);

        // TODO 写个getUserInfo, 根据username获得userInfo
        Object info =  user.get(username);

        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data",info);
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
}
