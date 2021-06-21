package com.csi.emphome.demo.service.test;

import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestTemp;

import org.springframework.stereotype.Service;
import java.util.HashMap;
@Service
public interface TestService {
    HashMap<String, Object> fetchListFunc(TestListQuery data);
    HashMap<String, Object> createListItemFunc(TestTemp data);
    HashMap<String, Object> updateListItemFunc(TestTemp data);
    HashMap<String, Object> deleteListItemFunc(TestTemp data);
}
