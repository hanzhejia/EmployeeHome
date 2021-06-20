package com.csi.emphome.demo.service.test;

import com.csi.emphome.demo.domain.test.test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    List<test> list();
}
