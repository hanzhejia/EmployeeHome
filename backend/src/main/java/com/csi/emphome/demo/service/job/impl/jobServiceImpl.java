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
        job tag_name = jobRepository.findByNAME(data.getNAME());
        System.out.println(tag_name);
        System.out.println("sdasd");
        System.out.println(tag_item);
        HashMap<String, Object> response = new HashMap<>();
        if (tag_item == null && tag_name == null){
            System.out.println(data);
            job temp_item = new job(data.getID(),data.getNAME(),data.getREMAKE());
            jobRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
            response.put("code",20000);
            response.put("data","sb");
            return response;
        }
        response.put("code",20000);
        response.put("data","sbs");
        System.out.println("1");
        return response;
    }


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

    @Override
    public HashMap<String, Object> deleteallListItemFunc(job[] data) {
        System.out.println("sddddddddddddadsadsadsa");
        int resCode = 20001;
        String resData = "failed";
        for(int i=0;i<data.length;i++){
        job tag_item = jobRepository.findById(data[i].getID());
        System.out.println(data[i].getID());
        if (tag_item != null){
            jobRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
}
