package com.csi.emphome.demo.service.user;

import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface UserService {
    HashMap<String, Object> fetchListFunc(UserListQuery data);
    HashMap<String, Object> createListItemFunc(UserItem data);
    HashMap<String, Object> updateListItem(UserItem data);
}
