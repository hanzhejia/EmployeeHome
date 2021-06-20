package com.csi.emphome.demo.rest.face;


import com.csi.emphome.demo.service.face.FaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/vue-admin-template/face")
public class face {
    private final FaceService faceService;

    public face(FaceService faceService) {
        this.faceService = faceService;
    }

    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList() {
        return faceService.fetchListFunc();
    }
}
