package com.csi.emphome.demo;
import com.csi.emphome.demo.domain.job.job;
import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.repository.job.jobRepository;
import com.csi.emphome.demo.service.job.jobService;
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
public class Job_test {
    @Autowired
    jobService jobService;
    @Test

    void fetchListFuncTest() {
        TestListQuery data = new TestListQuery(1, 10);
        HashMap<String, Object> res = jobService.fetchListFunc(data);
        System.out.println(res);
    }
    @Test
    void createListItemFuncTest() {
        job data = new job();
        data.setID(999);
        data.setNAME("NBNB");
        data.setREMAKE("无敌");
        HashMap<String, Object> res = jobService.createListItemFunc(data);
        System.out.println(res);
    }
    @Test
    void updateListItemFuncTest() {
        job data = new job();
        data.setID(999);
        data.setNAME("NBNB");
        data.setREMAKE("无敌的我");
        HashMap<String, Object> res =  jobService.updateListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void deleteListItemFuncTest() {
        job data = new job();
        data.setID(999);
        data.setNAME("NBNB");
        data.setREMAKE("无敌的我");
        HashMap<String, Object> res =  jobService.deleteListItemFunc(data);
        System.out.println(res);
    }

}
