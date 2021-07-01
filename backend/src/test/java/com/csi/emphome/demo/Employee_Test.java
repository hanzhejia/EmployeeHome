package com.csi.emphome.demo;

import com.csi.emphome.demo.service.employee.EmployeeService;
import com.csi.emphome.demo.service.employee.dto.EmployeeListQuery;
import com.csi.emphome.demo.service.employee.dto.EmployeeTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Employee_Test {
    @Autowired
    EmployeeService employeeService;

    @Test
    void fetchListFuncTest() {
        EmployeeListQuery data = new EmployeeListQuery(1, 10);
        HashMap<String, Object> res = employeeService.fetchListFunc(data);
        System.out.println(res);
    }

    @Test
    void createListItemFuncTest() {
        EmployeeTemp data = new EmployeeTemp();
        data.setId(9999);
        data.setDeptid(1);
        data.setJobid(8250);
        data.setName("熊杰");
        data.setCard("123456789120365248");
        data.setAddress("翻斗花园");
        data.setPost("629000");
        data.setTel("08256290000");
        data.setPhone("18574763526");
        data.setQqnum("123456");
        data.setEmail("1@qq.com");
        data.setSex(2);
        data.setParty("群众");
        data.setBirthday(new java.util.Date());
        data.setRace("乌坎达");
        data.setEducation("大专");
        data.setSpeciality("无");
        data.setHobby("看妹妹");
        data.setRemark("无");
        data.setCreatedate(new java.util.Date());
        HashMap<String, Object> res = employeeService.createListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void updateListItemFuncTest() {
        EmployeeTemp data = new EmployeeTemp();
        data.setId(9999);
        data.setName("熊二");
        data.setSex(2);
        data.setPhone("01234567895");
        data.setEmail("2@qq.com");
        data.setJobid(2220);
        data.setEducation("文盲");
        data.setCard("82082088201234567X");
        data.setDeptid(2);
        data.setAddress("河南封门村");
        data.setCreatedate(new Date());
        HashMap<String, Object> res = employeeService.updateListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void deleteListItemFuncTest() {
        EmployeeTemp data = new EmployeeTemp();
        data.setId(9999);
        data.setName("熊二");
        data.setSex(2);
        data.setPhone("01234567895");
        data.setEmail("2@qq.com");
        data.setJobid(2220);
        data.setEducation("文盲");
        data.setCard("82082088201234567X");
        data.setDeptid(2);
        data.setAddress("河南封门村");
        HashMap<String, Object> res = employeeService.deleteListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void searchListItemTest(){
        EmployeeTemp data = new EmployeeTemp();
        EmployeeListQuery data2 = new EmployeeListQuery(1, 10);
        data.setJobid(2220);
        data.setName("熊二");
        data.setCard("82082088201234567X");
        data.setSex(2);
        data.setPhone("01234567895");
        data.setDeptid(2);
        HashMap<String, Object> res = employeeService.searchListItemFunc(data,data2);
        System.out.println(res);
    }
}
