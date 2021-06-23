package com.csi.emphome.demo.service.noti.impl;

import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.repository.noti.notiRepository;
import com.csi.emphome.demo.service.noti.notiService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class notiServiceImpl implements notiService {
    private final notiRepository notiRepository;

    public notiServiceImpl(notiRepository notiRepository) {
        this.notiRepository = notiRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc(TestListQuery data) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();

        List listItems = notiRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
//        TestListQuery data
//        HashMap<String, Object> responseData = new HashMap<>();
//        List<noti> list = notiRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
//        System.out.println(notiRepository.count());
//        responseData.put("total",notiRepository.count());
//        responseData.put("items",list);
//        HashMap<String, Object> response = new HashMap<>();
//        response.put("code",20000);
//        response.put("data",responseData);
//        return response;
    }

    @Override
    public HashMap<String, Object> createListItemFunc(noti data) {
        int resCode = 20001;
        String resData = "failed";
        System.out.println(data);
        noti tag_item = notiRepository.findById(data.getId());
        System.out.println(tag_item);
        if (tag_item == null){
            System.out.println(data);
            noti temp_item = new noti(data.getId(),data.getTiitle(),data.getContent(),data.getCareTime(),data.getUserid());
            notiRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","sb");
        return response;
    }

    @Override
    public HashMap<String, Object> updateListItemFunc(noti data) {
        //        testRepository.save(data);
//        System.out.println(data);
        int resCode = 20001;
        String resData = "failed";
        noti tag_item = notiRepository.findById(data.getId());
        if (tag_item != null){
            tag_item.setId(data.getId());
            tag_item.setTiitle(data.getTiitle());
            tag_item.setContent(data.getContent());
            tag_item.setCaredate(data.getCareTime());
            tag_item.setUserid(data.getUserid());
            notiRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListItemFunc(noti data) {
        System.out.println("sddddddddddddadsadsadsa");
        int resCode = 20001;
        String resData = "failed";
        noti tag_item = notiRepository.findById(data.getId());
        System.out.println(data.getId());
        if (tag_item != null){
            notiRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
}
