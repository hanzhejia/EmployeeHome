//package com.csi.emphome.demo.rest.noti;
//
//
//import com.csi.emphome.demo.domain.notice.noti;
//import com.csi.emphome.demo.service.noti.notiService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.HashMap;
//
//@Controller
//@RequestMapping(value = "/vue-admin-template/noti")
//public class notiController {
//    private final notiService notiService;
//    public notiController(notiService notiService) {
//        this.notiService = notiService;
//    }
//    @CrossOrigin
//    @GetMapping(value = "/list")
//    @ResponseBody
//    public HashMap<String, Object> fetchList( ) {
//        System.out.println();
//        return notiService.fetchListFunc();
//    }
//    @CrossOrigin
//    @PostMapping(value = "/create")
//    @ResponseBody
//    public HashMap<String, Object> createListItem(@RequestBody noti data) {
//        System.out.println(data);
//        LocalDate date = LocalDate.now();
//        data.setCaredate(Date.valueOf(date));
//        return notiService.createListItemFunc(data);
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/update")
//    @ResponseBody
//    public HashMap<String, Object> updateListItem(@RequestBody noti data) {
//        LocalDate date = LocalDate.now();
//        data.setCaredate(Date.valueOf(date));
//        System.out.println("Sssssssssssssssssssssssss");
//        return notiService.updateListItemFunc(data);
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/delete")
//    @ResponseBody
//    public HashMap<String, Object> deleteListItem(@RequestBody noti data) {
//        LocalDate date = LocalDate.now();
//        data.setCaredate(Date.valueOf(date));
//        System.out.println("Sssssssssssssssssssssssss");
//        return notiService.deleteListItemFunc(data);
//    }
//}
package com.csi.emphome.demo.rest.noti;


import com.csi.emphome.demo.domain.notice.noti;
import com.csi.emphome.demo.service.noti.notiService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
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
    public HashMap<String, Object> fetchList(TestListQuery data) {
        System.out.println("噢噢噢噢噢噢噢噢");
        System.out.println(notiService.fetchListFunc(data));
        return notiService.fetchListFunc(data);
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
        System.out.println("Sssssssssssssssssssssssss");
        return notiService.updateListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody noti data) {
        LocalDate date = LocalDate.now();
        data.setCaredate(Date.valueOf(date));
        System.out.println("Sssssssssssssssssssssssss");
        return notiService.deleteListItemFunc(data);
    }
}
