package com.csi.emphome.demo.rest.test;

import com.csi.emphome.demo.common.Result;
import com.csi.emphome.demo.jwt.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getToken(){
        String token = JwtUtil.sign("xsy", "123");
        redisTemplate.opsForValue().set(token,token, expireTime*2/100, TimeUnit.SECONDS);
        return Result.success(token);
    }

    @RequiresPermissions("user:admin")
    @RequestMapping("/test")
    public Result test(){
        System.out.println("进入测试，只有带有令牌才可以进入该方法");
        return Result.success(1,"访问接口成功");
    }
}

