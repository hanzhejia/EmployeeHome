package com.csi.emphome.demo.rest.test;


import com.csi.emphome.demo.service.test.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/vue-admin-template/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> list() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = testService.list();
        response.put("code",20000);
        responseData.put("total",listItems.size());
        responseData.put("items",listItems);
        response.put("data",responseData);
        return response;
    }
}
