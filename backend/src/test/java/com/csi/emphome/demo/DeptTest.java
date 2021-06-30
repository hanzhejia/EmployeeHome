package com.csi.emphome.demo;

import com.csi.emphome.demo.service.dept.DeptService;
import com.csi.emphome.demo.service.dept.dto.DeptListQuery;
import com.csi.emphome.demo.service.dept.dto.DeptSearchData;
import com.csi.emphome.demo.service.dept.dto.DeptTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeptTest {
    @Autowired
    DeptService deptService;

    @Test
    void fetchListFuncTest() {
        DeptListQuery data = new DeptListQuery(1, 10);
        HashMap<String, Object> res = deptService.fetchListFunc(data);
        System.out.println(res);
    }

    @Test
    void fetchListItemFuncTest() {
        DeptSearchData data = new DeptSearchData();
        data.setSearch("123");
        data.setListQuery(new DeptListQuery(1, 10));
        HashMap<String, Object> res = deptService.fetchListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void createListItemFuncTest() {
        DeptTemp data = new DeptTemp();
        data.setId(999);
        data.setName("技术部");
        data.setRemark("123213123123");
        HashMap<String, Object> res = deptService.createListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void updateListItemFuncTest() {
        DeptTemp data = new DeptTemp();
        data.setId(999);
        data.setName("技术部");
        data.setRemark("123213123123");
        HashMap<String, Object> res = deptService.updateListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void deleteListItemFuncTest() {
        List<DeptTemp> data = new ArrayList<>();
        data.get(0).setId(111);
        data.get(0).setName("技术部");
        data.get(0).setRemark("123123123");
        data.get(1).setId(222);
        data.get(2).setName("财务部");
        data.get(3).setRemark("123123123");
        HashMap<String, Object> res = deptService.deleteListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void checkSameNameFuncTest() {
        DeptTemp data = new DeptTemp();
        HashMap<String, Object> res = deptService.checkSameNameFunc(data);
        System.out.println(res);
    }

    @Test
    void fetchDeptListTest() {
        HashMap<String, Object> res = deptService.fetchDeptList();
        System.out.println(res);
    }

}

