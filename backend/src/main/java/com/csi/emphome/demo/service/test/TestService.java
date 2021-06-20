package com.csi.emphome.demo.service.test;

import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface TestService {
    HashMap<String, Object> fetchListFunc();
}
