package com.csi.emphome.demo;

import com.csi.emphome.demo.service.user.UserService;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import com.csi.emphome.demo.service.user.dto.UserTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class User_test {
    @Autowired
    UserService userService;
    @Test
    void fetchListFuncTest() {
        UserListQuery data = new UserListQuery(1, 10);
        HashMap<String, Object> res = userService.fetchListFunc(data);
        System.out.println(res);
    }
    @Test
    void createListItemFuncTest(){
        UserTemp data = new UserTemp();
        data.setId(999);
        data.setLoginname("loginname");
        data.setPassword("password");
        data.setUsername("username");
        data.setStatus(2);
        data.setCreatedate(new Date(Calendar.getInstance().getTime().getTime()));
        data.setFaceurl("faceurl");
        data.setFacepath("facepath");
        HashMap<String,Object> res = userService.createListItemFunc(data);
        System.out.println(res);

    }
    @Test
    void updateListItemFuncTest(){
        UserTemp data = new UserTemp();
        data.setId(999);
        data.setLoginname("loginname");
        data.setPassword("password");
        data.setUsername("username");
        data.setStatus(2);
        data.setCreatedate(new Date(Calendar.getInstance().getTime().getTime()));
        data.setFaceurl("faceurl");
        data.setFacepath("facepath");
        HashMap<String,Object> res = userService.updateListItemFunc(data);
        System.out.println(res);
    }
    @Test
    void deleteListItemFuncTest(){
        List<UserTemp> data = new ArrayList<>();
        data.add(new UserTemp(2,"","",2,new Date(Calendar.getInstance().getTime().getTime()),"","",""));
        HashMap<String,Object> res = userService.deleteListItemFunc(data);
        System.out.println(res);
    }
    @Test
    void checkSameNameFuncTest(){
        UserTemp data = new UserTemp();
        data.setId(999);
        data.setLoginname("loginname");
        data.setPassword("password");
        data.setUsername("username");
        data.setStatus(2);
        data.setCreatedate(new Date(Calendar.getInstance().getTime().getTime()));
        data.setFaceurl("faceurl");
        data.setFacepath("facepath");
        HashMap<String,Object> res = userService.checkSameNameFunc(data);
        System.out.println(res);
    }

}
