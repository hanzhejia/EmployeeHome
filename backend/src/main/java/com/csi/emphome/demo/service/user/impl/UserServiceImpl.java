package com.csi.emphome.demo.service.user.impl;

import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.repository.user.UserRepository;
import com.csi.emphome.demo.service.user.UserService;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import com.csi.emphome.demo.service.user.dto.UserSearchData;
import com.csi.emphome.demo.service.user.dto.UserTemp;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public HashMap<String, Object> fetchListFunc(UserListQuery data) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = userRepository.findAll();
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }
    public int getMaxId(){
        UserItem tag_item = userRepository.findTopByOrderByIdDesc();
        int max_id = tag_item.getId();
        return max_id;
    }
    public Date getDate()
    {
        Date time = new Date(System.currentTimeMillis());
        return time;
    }
    @Override
    public HashMap<String, Object> createListItemFunc(UserTemp data) {
        int resCode = 20001;
        String resData = "failed";
        int new_id = 1;
        Date createdate = getDate();
        int count = userRepository.findAll().size();
        if (count != 0){
            new_id = getMaxId() + 1;
        }
        System.out.println("添加");
        System.out.println(data);
        if (data != null){
            UserItem temp_item = new UserItem(new_id, data.getLoginname(),data.getPassword(), data.getStatus(), createdate,data.getUsername(),data.getFaceurl(),data.getFacepath());
            userRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListItemFunc(UserTemp data) {

        int resCode = 20001;
        String resData = "deletefailed";
        UserItem uitem = userRepository.findById(data.getId());

        if (uitem != null){
            userRepository.delete(uitem);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }
    public static List<UserItem> splicePage(List<UserItem> list, Integer pageNum, Integer pageSize) {
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
    public HashMap<String, Object> updateListItemFunc(UserTemp data) {
        int resCode = 20001;
        String resData = "updatefailed";
        UserItem uitem = userRepository.findById(data.getId());
        if (uitem != null){
            uitem.setId(data.getId());
            uitem.setLoginname(data.getLoginname());
            uitem.setUsername(data.getUsername());
            uitem.setPassword(data.getPassword());
            uitem.setCreatedate(data.getCreatedate());
            uitem.setStatus(data.getStatus());
            uitem.setFaceurl(data.getFaceurl());
            uitem.setFacepath(data.getFacepath());
            userRepository.save(uitem);
            resCode = 20000;
            resData = "success";
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }


    @Override
    public HashMap<String, Object> fetchListItemFunc(UserSearchData data) {
        System.out.println(data);
        HashMap<String, Object> responseData = new HashMap<>();

        if(data.getSearch().equals("")){
            return fetchListFunc(data.getListQuery());
        }

        List<UserItem> listAll = userRepository.findAllByNameLike("%" + data.getSearch() + "%");
        List<UserItem> list;
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

}
