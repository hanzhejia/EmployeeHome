package com.csi.emphome.demo.rest.test;

import com.csi.emphome.demo.jwt.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * shiro控制器
 *
 * @author xiaosongyue
 * @date 2021/04/01 14:48:40
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${custom.jwt.expire_time}")
    private long expireTime;

    @RequestMapping("/getToken")
    public HashMap<String, Object> getToken() {
        String token = JwtUtil.sign("editor", "111111");
        redisTemplate.opsForValue().set(token,token, expireTime*2/100, TimeUnit.SECONDS);
        HashMap<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", "OK");
        return response;
    }

    @RequiresPermissions("user:admin")
    @RequestMapping("/test")
    public HashMap<String, Object> test() {
        System.out.println("Admin进入测试，只有带有令牌才可以进入该方法");
        HashMap<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("data", "访问接口成功");
        return response;
    }
}

