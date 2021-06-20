package com.csi.emphome.demo.service.login;

import com.csi.emphome.demo.domain.test.test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginService {
    List<test> list();
}
