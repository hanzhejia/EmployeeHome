package com.csi.emphome.demo;

import com.csi.emphome.demo.service.test.TestService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestSearchData;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestTest {
    @Autowired
    TestService testService;

    @Test
    void fetchListFuncTest() {
        TestListQuery data = new TestListQuery(1, 10);
        HashMap<String, Object> res = testService.fetchListFunc(data);
        System.out.println(res);
    }

    @Test
    void fetchListItemFuncTest() {
        TestSearchData data = new TestSearchData();
        data.setSearch("NB");
        data.setListQuery(new TestListQuery(1, 10));
        HashMap<String, Object> res = testService.fetchListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void createListItemFuncTest() {
        TestTemp data = new TestTemp();
        data.setId(999);
        data.setInfo("NBNB");
        data.setTime(new Date(Calendar.getInstance().getTime().getTime()));
        HashMap<String, Object> res = testService.createListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void updateListItemFuncTest() {
        TestTemp data = new TestTemp();
        data.setId(999);
        data.setInfo("NBNBNB2");
        data.setTime(new Date(Calendar.getInstance().getTime().getTime()));
        HashMap<String, Object> res = testService.updateListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void deleteListItemFuncTest() {
        TestTemp data = new TestTemp();
        data.setId(999);
        data.setInfo("NBNBNB2");
        data.setTime(new Date(Calendar.getInstance().getTime().getTime()));
        HashMap<String, Object> res = testService.deleteListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void apiTestNormalFuncTest() {
        HashMap<String, Object> res = testService.apiTestNormalFunc();
        System.out.println(res);
    }

    @Test
    void apiTestAdminFuncTest() {
        HashMap<String, Object> res = testService.apiTestAdminFunc();
        System.out.println(res);
    }
}
