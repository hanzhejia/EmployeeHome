package com.csi.emphome.demo.service.job.impl;

import com.csi.emphome.demo.domain.job.job;
import com.csi.emphome.demo.repository.job.jobRepository;
import com.csi.emphome.demo.service.job.jobService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class jobServiceImpl implements jobService {
    private final jobRepository jobRepository;

    public jobServiceImpl(jobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public HashMap<String, Object> fetchListFunc(TestListQuery data) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = jobRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String, Object> fetchJobList() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = jobRepository.findAll();
        System.out.println(listItems);
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String,Object> createListItemFunc(job data){
        int resCode = 20001;
        String resData = "failed";
        System.out.println(data);
        job tag_item = jobRepository.findById(data.getID());
        System.out.println(tag_item);
        job tag_NAME = jobRepository.findByNAME(data.getNAME());
        System.out.println(tag_NAME);
        System.out.println("SDASD");
        if (tag_item == null){
            System.out.println(data);
            job temp_item = new job(data.getID(),data.getNAME(),data.getREMAKE());
            jobRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","sb");
        return response;
    }
//    @Override
//    public HashMap<String, Object> createListItemFunc(job data) {
//        int resCode = 20001;
//        String resData = "failed";
//        System.out.println(data);
//        job tag_item = jobRepository.findById(data.getId());
//        System.out.println(tag_item);
//        if (tag_item == null){
//            System.out.println(data);
//            job temp_item = new job(data.getId(),data.getNAME(),data.getREMAKE());
//            jobRepository.save(temp_item);
//            resCode = 20000;
//            resData = "success";
//        }
//        HashMap<String, Object> response = new HashMap<>();
//        response.put("code",20000);
//        response.put("data","sb");
//        return response;
//    }

    @Override
    public HashMap<String, Object> updateListItemFunc(job data) {
        //        testRepository.save(data);
//        System.out.println(data);
        int resCode = 20001;
        String resData = "failed";
        job tag_item = jobRepository.findById(data.getID());
        if (tag_item != null){
//            tag_item.setId(data.getId());
            tag_item.setNAME(data.getNAME());
            tag_item.setREMAKE(data.getREMAKE());
            jobRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListItemFunc(job data) {
        System.out.println("sddddddddddddadsadsadsa");
        int resCode = 20001;
        String resData = "failed";
        job tag_item = jobRepository.findById(data.getID());
        System.out.println(data.getID());
        if (tag_item != null){
            jobRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
}
