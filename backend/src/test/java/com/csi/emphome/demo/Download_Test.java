package com.csi.emphome.demo;

import com.csi.emphome.demo.service.download.DownloadService;
import com.csi.emphome.demo.service.download.dto.DownloadListQuery;
import com.csi.emphome.demo.service.download.dto.DownloadSearchData;
import com.csi.emphome.demo.service.download.dto.DownloadTemp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Download_Test {
    @Autowired
    DownloadService downloadService;

    @Test
    void fetchListFuncTest() {
        DownloadListQuery data = new DownloadListQuery(1, 10);
        HashMap<String, Object> res = downloadService.fetchListFunc(data);
        System.out.println(res);
    }

    @Test
    void fetchListItemFuncTest() {
        DownloadSearchData data = new DownloadSearchData();
        data.setSelect("1");
        data.setSearch("NB");
        data.setListQuery(new DownloadListQuery(1, 10));
        HashMap<String, Object> res = downloadService.fetchListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void createListItemFuncTest() {
        DownloadTemp data = new DownloadTemp();
        data.setStorageId(114);
        data.setRealName("nb");
        data.setName("nb2");
        data.setCreateBy("ssssss");
        data.setCreateTime("1625044315");
        data.setDetail("sdadasdas");
        HashMap<String, Object> res = downloadService.createListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void updateListItemFuncTest() {
        DownloadTemp data = new DownloadTemp();
        data.setStorageId(114);
        data.setRealName("nb2");
        data.setName("nb");
        data.setCreateBy("ssssss");
        data.setCreateTime("1625044315");
        data.setDetail("sdadasdas");
        HashMap<String, Object> res = downloadService.updateListItemFunc(data);
        System.out.println(res);
    }

    @Test
    void deleteListFuncTest() {
        DownloadTemp data2 = new DownloadTemp();
        data2.setStorageId(114);
        data2.setRealName("nb2");
        data2.setName("nb");
        data2.setCreateBy("ssssss");
        data2.setCreateTime("1625044315");
        data2.setDetail("sdadasdas");
        ArrayList<DownloadTemp> data = new ArrayList<>();

        data.add(data2);
        HashMap<String, Object> res = downloadService.deleteListFunc(data);
        System.out.println(res);
    }

    @Test
    void uploadFileFuncTest() {
        String res = "ok";
//        HashMap<String, Object> res = downloadService.uploadFileFunc(data);
        System.out.println(res);
    }
}
