package com.csi.emphome.demo.service.test.impl;

import com.csi.emphome.demo.domain.test.test;
import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.test.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<test> list() {
        return testRepository.findAll();
    }
}
