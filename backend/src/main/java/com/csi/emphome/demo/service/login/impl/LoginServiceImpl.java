package com.csi.emphome.demo.service.login.impl;

import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.repository.test.TestRepository;
import com.csi.emphome.demo.service.login.LoginService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private final TestRepository testRepository;

    public LoginServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<TestItem> list() {
        return testRepository.findAll();
    }
}
