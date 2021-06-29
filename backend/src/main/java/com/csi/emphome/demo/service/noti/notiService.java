package com.csi.emphome.demo.service.noti;

import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface notiService {
//    List<noti> list();
HashMap<String, Object> fetchListFunc(TestListQuery data);
    HashMap<String, Object> createListItemFunc(noti data);
    HashMap<String, Object> updateListItemFunc(noti data);
    HashMap<String, Object> deleteListItemFunc(noti data);
    HashMap<String, Object> deleteallListItemFunc(noti[] data);
}
