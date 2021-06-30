package com.csi.emphome.demo;
import com.csi.emphome.demo.domain.job.job;
import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.repository.noti.notiRepository;
import com.csi.emphome.demo.service.noti.notiService;

import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Notice_test {
    @Autowired
    notiService notiService;
    @Test

    void fetchListFuncTest() {
        TestListQuery data = new TestListQuery(1, 10);
        HashMap<String, Object> res = notiService.fetchListFunc(data);
        System.out.println(res);
    }

    @Test
    void createListItemFuncTest() {
        noti data = new noti();
        data.setId(999);
        data.setTiitle("NBNB");
        data.setContent("无敌");
        data.setCaredate(new Date(Calendar.getInstance().getTime().getTime()));
        int a=1;
        data.setUserid(a);
        HashMap<String, Object> res = notiService.createListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void updateListItemFuncTest() {
        noti data = new noti();
        data.setId(999);
        data.setTiitle("NBNB");
        data.setContent("无敌是多么寂寞");
        data.setCaredate(new Date(Calendar.getInstance().getTime().getTime()));
        data.setUserid(1);
        HashMap<String, Object> res = notiService.updateListItemFunc(data);
        System.out.println(res);
    }
    @Test
    void deleteListItemFuncTest() {
        noti data = new  noti();
        data.setId(999);
        data.setTiitle("NBNB");
        data.setContent("无敌是多么寂寞");
        data.setCaredate(new Date(Calendar.getInstance().getTime().getTime()));
        data.setUserid(1);
        HashMap<String, Object> res =  notiService.deleteListItemFunc(data);
        System.out.println(res);
    }


}
