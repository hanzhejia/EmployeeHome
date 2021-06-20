package com.csi.emphome.demo.rest.noti;


import com.csi.emphome.demo.service.noti.notiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

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
    public HashMap<String, Object> list() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = notiService.list();
        response.put("code",20000);
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("data",responseData);
        System.out.println("sd1");
        System.out.println(listItems);
        return response;

    }
}
