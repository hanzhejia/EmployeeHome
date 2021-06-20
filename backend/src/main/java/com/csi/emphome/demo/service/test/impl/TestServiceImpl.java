package com.csi.emphome.demo.service.test.impl;

import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.test.TestService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestTemp;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc(TestListQuery data) {
        HashMap<String, Object> responseData = new HashMap<>();
        List<TestItem> list = testRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
        responseData.put("total",testRepository.count());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String, Object> createListItemFunc(TestTemp data) {
        int resCode = 20001;
        String resData = "failed";

        TestItem tag_item = testRepository.findById(data.getId());
        if (tag_item == null){
            TestItem temp_item = new TestItem(data.getId(), data.getInfo(), data.getTime());
            testRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> updateListItemFunc(TestTemp data) {
        int resCode = 20001;
        String resData = "failed";

        TestItem tag_item = testRepository.findById(data.getId());
        if (tag_item != null){
            tag_item.setId(data.getId());
            tag_item.setInfo(data.getInfo());
            tag_item.setTime(data.getTime());
            testRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListItemFunc(TestTemp data) {
        int resCode = 20001;
        String resData = "failed";

        TestItem tag_item = testRepository.findById(data.getId());
        if (tag_item != null){
            testRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
}
