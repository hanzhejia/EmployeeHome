package com.csi.emphome.demo.service.dept.impl;

import com.csi.emphome.demo.domain.dept.DeptItem;
import com.csi.emphome.demo.domain.test.TestItem;
import com.csi.emphome.demo.repository.dept.DeptRepository;
import com.csi.emphome.demo.service.dept.DeptService;
import com.csi.emphome.demo.service.dept.dto.DeptListQuery;
import com.csi.emphome.demo.service.dept.dto.DeptSearchData;
import com.csi.emphome.demo.service.dept.dto.DeptTemp;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public HashMap<String, Object> fetchListFunc(DeptListQuery data) {
        HashMap<String, Object> responseData = new HashMap<>();
        List<DeptItem> list = deptRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
        responseData.put("total",deptRepository.count());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String, Object> fetchDeptList() {
        HashMap<String, Object> responseData = new HashMap<>();
        List<DeptItem> list = deptRepository.findAll();
        responseData.put("total",deptRepository.count());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }


    public static List<DeptItem> splicePage(List<DeptItem> list, Integer pageNum, Integer pageSize) {
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }

        Integer count = list.size(); //记录总数
        Integer pageCount = 0; //页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引

        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        return list.subList(fromIndex, toIndex);
    }

    @Override
    public HashMap<String, Object> fetchListItemFunc(DeptSearchData data) {
        System.out.println(data);
        HashMap<String, Object> responseData = new HashMap<>();

        if(data.getSearch().equals("")){
            return fetchListFunc(data.getListQuery());
        }

        List<DeptItem> listAll = deptRepository.findAllByNameLike("%" + data.getSearch() + "%");
        List<DeptItem> list;
        if(listAll.size()>0){
            list = splicePage(listAll, data.getListQuery().getPage(), data.getListQuery().getLimit());
        }else {
            list = listAll;
        }

        responseData.put("total",listAll.size());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    public int getMaxId(){
        DeptItem tag_item = deptRepository.findTopByOrderByIdDesc();
        int max_id = tag_item.getId();
        return max_id;
    }
    @Override
    public HashMap<String, Object> createListItemFunc(DeptTemp data) {
        int resCode = 20001;
        String resData = "failed";
        int new_id = 1;
        int count = deptRepository.findAll().size();
        if (count != 0){
            new_id = getMaxId() + 1;
        }
        if (data != null){
            DeptItem temp_item = new DeptItem(new_id, data.getName(), data.getRemark());
            deptRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListItemFunc(DeptTemp data) {
        int resCode = 20001;
        String resData = "failed";
        DeptItem tag_item = deptRepository.findById(data.getId());

        if (tag_item != null){
            deptRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> updateListItemFunc(DeptTemp data) {
        int resCode = 20001;
        String resData = "failed";
        DeptItem tag_item = deptRepository.findById(data.getId());
        if (tag_item != null){
            tag_item.setId(data.getId());
            tag_item.setName(data.getName());
            tag_item.setRemark(data.getRemark());
            deptRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
}
