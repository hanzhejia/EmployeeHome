package com.csi.emphome.demo.rest.face;


import com.csi.emphome.demo.domain.face.Face;
import com.csi.emphome.demo.service.face.FaceService;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
@Controller
@RequestMapping(value = "/vue-admin-template/face")
public class FaceController {
    private final FaceService faceService;

    public FaceController(FaceService faceService) {
        this.faceService = faceService;
    }

    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> faceList() {
        return faceService.faceListFunc();
    }

    @CrossOrigin
    @PostMapping(value = "/find")
    @ResponseBody
    public HashMap<String, Object> faceResult(@RequestBody Face nowbase64) throws JSONException {
        return faceService.serchFace(nowbase64);
    }

    @CrossOrigin
    @PostMapping(value = "/add")
    @ResponseBody
    public HashMap<String, Object> faceAdd(@RequestBody Face nowbase64) {
        return faceService.addFace(nowbase64);
    }
}
