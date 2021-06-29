package com.csi.emphome.demo.rest.job;

import com.csi.emphome.demo.domain.job.job;
import com.csi.emphome.demo.service.job.jobService;
import com.csi.emphome.demo.service.test.dto.TestListQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/job")
public class jobController {
    private final jobService jobService;

    public jobController(jobService jobService) {
        this.jobService = jobService;
    }


    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList( TestListQuery data) {
        return jobService.fetchListFunc(data);
    }
//    @CrossOrigin
//    @PostMapping(value = "/listItem")
//    @ResponseBody
//    public HashMap<String, Object> fetchList(@RequestBody TestSearchData data) {
//        return jobService.fetchListItemFunc(data);
//    }

    @CrossOrigin
    @PostMapping(value = "/joblist")
    @ResponseBody
    public HashMap<String, Object> fetchJobList() {
        return jobService.fetchJobList();
    }


    @CrossOrigin
    @GetMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(job data) {
        System.out.println("sddddddddddddddddddd");
        System.out.println(data);
        System.out.println("sddddddddddddddddddd");
        return jobService.createListItemFunc(data);
    }
    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody job data) {
        return jobService.updateListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody job data) {
        return jobService.deleteListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/deletell")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody job[] data) {
        System.out.println("sb");
        System.out.println(data[0].toString());
        return jobService.deleteallListItemFunc(data);
    }

}
