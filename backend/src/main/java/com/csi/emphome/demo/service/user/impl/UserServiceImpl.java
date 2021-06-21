package com.csi.emphome.demo.service.user.impl;
import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.repository.user.UserRepository;
import com.csi.emphome.demo.service.user.UserService;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import org.springframework.stereotype.Service;

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

    @Override
    public HashMap<String, Object> createListItemFunc(UserItem data) {
        System.out.println(data);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    @Override
    public HashMap<String, Object> updateListItem(UserItem data) {
        return null;
    }
}
