package com.csi.emphome.demo.service.test;

import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface TestService {
    HashMap<String, Object> fetchListFunc();
    HashMap<String, Object> createListItemFunc(TestItem data);
}
