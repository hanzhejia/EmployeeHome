package com.csi.emphome.demo.service.noti;

import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.domain.test.TestItem;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface notiService {
//    List<noti> list();
HashMap<String, Object> fetchListFunc();
    HashMap<String, Object> createListItemFunc(noti data);
    HashMap<String, Object> updateListItemFunc(noti data);
}
