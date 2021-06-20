package com.csi.emphome.demo.service.face.impl;

import com.csi.emphome.demo.domain.test.test;
import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.test.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceServiceImpl implements TestService {
    private final TestRepository testRepository;

    public FaceServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<test> list() {
        return testRepository.findAll();
    }
}
