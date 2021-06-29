package com.csi.emphome.demo.service.face;

import com.csi.emphome.demo.domain.face.Face;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;

@Service
public interface FaceService {
    HashMap<String, Object> faceListFunc();
    HashMap<String, Object> serchFace(Face nowbase64) throws JSONException;
    HashMap<String, Object> addFace(Face nowbase64) throws JSONException;
    HashMap<String, Object> delFace(Face nowface);
}
