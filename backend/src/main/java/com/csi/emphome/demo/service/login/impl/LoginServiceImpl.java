package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.user.UserItem;
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
        UserItem loginTemp= loginRepo.findByLoginname(data.getUsername());
        if(loginTemp!=null){
            if(loginTemp.getLoginname().equals(data.getUsername()) && loginTemp.getPassword().equals(data.getPassword())){
                HashMap<String, Object> token = new HashMap<>();
                String token_str = JwtUtil.sign(loginTemp.getLoginname(), loginTemp.getPassword());
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
        UserItem loginTemp= loginRepo.findByLoginname(username);

        HashMap<String, Object> user = new HashMap<>();
        ArrayList<String> roles = new ArrayList<>();
        if(loginTemp.getStatus() == 1){
            roles.add("admin");
        }else {
            roles.add("editor");
        }
        user.put("roles", roles);
        user.put("introduction", "I am a SB");
        user.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.put("name", loginTemp.getUsername());

        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data", user);
        return response;
    }

    @Override
    public HashMap<String, Object> updateLoginPwdFunc(PwdTemp data) {
        System.out.println("updateLoginPwdFunc"+data);
        UserItem login= loginRepo.findByLoginname(data.getUsername());
        login.setPassword(data.getNewpassword1());
        loginRepo.save(login);
        System.out.println(login);
        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }
}
