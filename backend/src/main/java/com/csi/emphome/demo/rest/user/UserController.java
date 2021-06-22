package com.csi.emphome.demo.rest.user;
import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.service.user.UserService;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
    /*显示*/
    @CrossOrigin
    @PostMapping(value = "/list")
    @ResponseBody
    public HashMap<String,Object> fetchList(@RequestBody UserListQuery data){
        return userService.fetchListFunc(data);
    }
    /*添加*/
    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody UserItem data) {
        return userService.createListItemFunc(data);
    }
}
