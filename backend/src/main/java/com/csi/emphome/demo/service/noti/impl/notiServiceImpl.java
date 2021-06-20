package com.csi.emphome.demo.service.noti.impl;

import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.repository.noti.notiRepository;
import com.csi.emphome.demo.service.noti.notiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class notiServiceImpl implements notiService {
    private final notiRepository notiRepository;

    public notiServiceImpl(notiRepository notiRepository) {
        this.notiRepository = notiRepository;
    }

    @Override
    public List<noti> list() {
        System.out.println("sd");
        System.out.println(notiRepository.findAll());
        return notiRepository.findAll();
    }
}
