package com.csi.emphome.demo.service.face.impl;

import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.face.FaceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FaceServiceImpl implements FaceService {
    private final TestRepository faceRepository;

    public FaceServiceImpl(TestRepository faceRepository) {
        this.faceRepository = faceRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = faceRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }
}
