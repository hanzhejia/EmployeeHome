package com.csi.emphome.demo.rest.noti;


import com.csi.emphome.demo.domain.notice.noti;

import com.csi.emphome.demo.service.noti.notiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/noti")
public class notiController {
    private final notiService notiService;

    public notiController(notiService notiService) {
        this.notiService = notiService;
    }
    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList() {
        return notiService.fetchListFunc();
    }
    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody noti data) {
        return notiService.createListItemFunc(data);
    }
}
