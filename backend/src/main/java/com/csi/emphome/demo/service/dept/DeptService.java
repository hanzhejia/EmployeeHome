package com.csi.emphome.demo.service.dept;
import com.csi.emphome.demo.service.dept.dto.DeptListQuery;
import com.csi.emphome.demo.service.dept.dto.DeptSearchData;
import com.csi.emphome.demo.service.dept.dto.DeptTemp;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public interface DeptService {
    HashMap<String, Object> fetchListFunc(DeptListQuery data);
    HashMap<String, Object> fetchListItemFunc(DeptSearchData data);
    HashMap<String, Object> createListItemFunc(DeptTemp data);
    HashMap<String, Object> deleteListItemFunc(List<DeptTemp> data);
    HashMap<String, Object> updateListItemFunc(DeptTemp data);
    HashMap<String, Object> checkSameNameFunc(DeptTemp data);
    HashMap<String, Object> fetchDeptList();
}
