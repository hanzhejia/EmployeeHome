package com.csi.emphome.demo.service.noti.impl;

import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.repository.noti.notiRepository;
import com.csi.emphome.demo.service.noti.notiService;
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
    public HashMap<String, Object> fetchListFunc() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = notiRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }
}
