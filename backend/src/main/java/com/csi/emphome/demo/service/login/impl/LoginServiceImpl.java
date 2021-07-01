package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.utils.jwt.JwtUtil;
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

/**
 * LoginServiceImpl:用户登录
 *
 * @author LJ
 * @date 2021/06/18
 *
 * */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${custom.jwt.expire_time}")
    private long expireTime;
    private final LoginRepo loginRepo;
    private static final HashMap<String, Object> response=new HashMap<>();
    public LoginServiceImpl(LoginRepo loginRepo){this.loginRepo=loginRepo;}
    /**
     * 判断账号密码是否正确
     *
     * @param data 账号和密码
     * @return 正确信息或错误信息
     * */
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
        response.put("code",20001);
        response.put("message","Account and password are incorrect.");
        return response;
    }
    /**
     * 获取用户信息
     *
     * @param data 用户账号和密码
     * @return 用户基本信息，权限，介绍，用户名等
     * */
    @Override
    public HashMap<String, Object> getLoginInfoFunc(String data) {
        String username = JwtUtil.getUsername(data);
        UserItem loginTemp= loginRepo.findByLoginname(username);

        HashMap<String, Object> user = new HashMap<>();
        ArrayList<String> roles = new ArrayList<>();
        if(loginTemp.getStatus() == 1){
            roles.add("admin");
        }else {
            roles.add("normal");
        }
        user.put("roles", roles);
        user.put("introduction", "I am NB");
        user.put("avatar", "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        user.put("name", loginTemp.getUsername());

        HashMap<String, Object> response=new HashMap<>();
        response.put("code",20000);
        response.put("data", user);
        return response;
    }

    /**
     * 修改密码
     *
     * @param data 账号，原密码和新密码
     * @return 修改成功或失败
     * */
    @Override
    public HashMap<String, Object> updateLoginPwdFunc(PwdTemp data) {
        System.out.println("updateLoginPwdFunc"+data);
        String username = JwtUtil.getUsername(data.getToken());
        UserItem login= loginRepo.findByLoginname(username);
        if (login.getPassword().equals(data.getPassword())){
            login.setPassword(data.getNewpassword1());
            loginRepo.save(login);
            System.out.println(login);
            HashMap<String, Object> response=new HashMap<>();
            response.put("code",20000);
            response.put("data","success");
            return response;
        }
        response.put("code",20000);
        response.put("data","false");
        return response;

    }
}
