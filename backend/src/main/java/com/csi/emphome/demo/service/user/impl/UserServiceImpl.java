package com.csi.emphome.demo.service.user.impl;

import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.repository.user.UserRepository;
import com.csi.emphome.demo.service.user.UserService;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import com.csi.emphome.demo.service.user.dto.UserSearchData;
import com.csi.emphome.demo.service.user.dto.UserTemp;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 用户管理后台处理
 * @author pengyao
 * @date 2021/6/30 16:15:21
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     *初始化分页，仅在页面最初生成时使用
     * @param data 表单页号和限制行数
     * @return 单页列表
     */
    @Override
    public HashMap<String, Object> fetchListFunc(UserListQuery data) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List<UserItem> list= userRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
        System.out.println(list);
        responseData.put("total",userRepository.count());
        responseData.put("items",list);
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    /**
     * 分页
     * @param list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List<UserItem> splicePage(List<UserItem> list, Integer pageNum, Integer pageSize) {
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }

        Integer count = list.size(); //记录总数
        System.out.println("数据总数");
        System.out.println(count);
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

    /**
     * 获取最大id
     * @return 最大的id
     */
    public int getMaxId(){
        UserItem tag_item = userRepository.findTopByOrderByIdDesc();
        int max_id = tag_item.getId();
        return max_id;
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public Date getDate()
    {
        Date time = new Date(System.currentTimeMillis());
        return time;
    }
    /**
     * 增加用户
     * @param data 前端页面增加用户功能传入数据
     * @return 成功或失败参数
     */
    @Override
    public HashMap<String, Object> createListItemFunc(UserTemp data) {
        int resCode = 20001;
        String resData = "failed";
        int new_id = 0;
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

    /**
     * 删除用户
     * @param data 前端页面删除用户功能传入数据
     * @return 成功或失败参数
     */
    @Override
    public HashMap<String, Object> deleteListItemFunc(List<UserTemp> data) {
        HashMap<String, Object> response = new HashMap<>();

        for (UserTemp datum : data) {
            UserItem tag_item = userRepository.findById(datum.getId());
            if (tag_item != null) {
                userRepository.delete(tag_item);
            } else {
                response.put("code", 20001);
                response.put("data", "failed");
                return response;
            }
        }
        response.put("code", 20000);
        response.put("data", "success");
        return response;
    }
    /**
     * 更新用户数据
     * @param data 前端页面编辑用户功能传入数据
     * @return 成功或失败参数
     */
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

    /**
     * 查询用户
     * @param data 前端页面查询用户功能传入数据
     * @return 符合条件的用户列表
     */
    @Override
    public HashMap<String, Object> fetchListItemFunc(UserSearchData data) {
        System.out.println(data);
        HashMap<String, Object> responseData = new HashMap<>();

        if(data.getSearch().equals("")){
            return fetchListFunc(data.getListQuery());
        }
        List<UserItem> listAll = userRepository.findAllByUsernameLike("%" + data.getSearch() + "%");
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

    /**
     * 判断用户名是否已经存在
     * @param data 前端页面查询用户功能传入数据
     * @return 存在返回相应提示，不存在则添加成功
     */
    @Override
    public HashMap<String, Object> checkSameNameFunc(UserTemp data) {
        int resCode;
        String resData;
        HashMap<String, Object> response = new HashMap<>();
        List<UserItem> item = userRepository.findAllByUsernameLike(data.getUsername());
        if(item.size() == 0){
            resCode = 20000;
            resData = "success";
        }else {
            resCode = 20000;
            resData = "failed";
        }
        response.put("code",resCode);
        response.put("data",resData);
        System.out.println(response);
        return response;
    }

}
