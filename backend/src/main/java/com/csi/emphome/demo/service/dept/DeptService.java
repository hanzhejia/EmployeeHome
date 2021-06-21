package com.csi.emphome.demo.service.dept;
import com.csi.emphome.demo.service.dept.dto.DeptListQuery;
import com.csi.emphome.demo.service.dept.dto.DeptSearchData;
import com.csi.emphome.demo.service.dept.dto.DeptTemp;
import com.csi.emphome.demo.service.test.dto.TestSearchData;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface DeptService {
    HashMap<String, Object> fetchListFunc(DeptListQuery data);
    HashMap<String, Object> fetchListItemFunc(DeptSearchData data);
    HashMap<String, Object> createListItemFunc(DeptTemp data);
    HashMap<String, Object> deleteListItemFunc(DeptTemp data);
    HashMap<String, Object> updateListItemFunc(DeptTemp data);
}
