package com.csi.emphome.demo.rest.noti;


import com.csi.emphome.demo.domain.notice.noti;

import com.csi.emphome.demo.service.noti.notiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
        LocalDate date = LocalDate.now();
        data.setCaredate(Date.valueOf(date));

        return notiService.createListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody noti data) {
        LocalDate date = LocalDate.now();
        data.setCaredate(Date.valueOf(date));
        return notiService.createListItemFunc(data);
    }
}
