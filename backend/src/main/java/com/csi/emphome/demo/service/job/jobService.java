package com.csi.emphome.demo.service.job;

import com.csi.emphome.demo.domain.job.job;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface jobService {
    //    List<noti> list();
    HashMap<String, Object> fetchListFunc(TestListQuery data);
    HashMap<String, Object> createListItemFunc(job data);
    HashMap<String, Object> updateListItemFunc(job data);
    HashMap<String, Object> deleteListItemFunc(job data);
    HashMap<String, Object> fetchJobList();
}
