package com.csi.emphome.demo.service.face.impl;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.csi.emphome.demo.domain.face.Face;
import com.csi.emphome.demo.repository.face.FaceRepository;
import com.csi.emphome.demo.service.face.FaceService;
import com.csi.emphome.demo.service.face.util.BaiduAIPCommon;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class FaceServiceImpl implements FaceService {
    private final FaceRepository faceRepository;

    public FaceServiceImpl(FaceRepository faceRepository) {
        this.faceRepository = faceRepository;
    }

    @Override
    public HashMap<String, Object> faceListFunc() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List listItems = faceRepository.findAll();
        responseData.put("items",listItems);
        response.put("code",20000);
        response.put("message","face");
        response.put("data",responseData);
        return response;
    }
    @Override
    public HashMap<String, Object> serchFace(Face nowbase64) throws JSONException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List<Face> listItems = faceRepository.findAll();
        AipFace client = new AipFace(BaiduAIPCommon.APP_FACE_ID, BaiduAIPCommon.API_FACE_KEY, BaiduAIPCommon.SECRET_FACE_KEY);
//        Map entry = (Map) listItems.get(0);
//        String userface = entry.get("base64").toString();
//        System.out.println(userface);
//        String imgStr2 = listItems.get(0).getBase64();
//        String imgStr = nowbase64.substring(0, nowbase64.length() - 1);
        String imgStr = nowbase64.getBase64();
//        System.out.print(imgStr);
//        System.out.print("\nimgStr");

//        MatchRequest req1 = new MatchRequest(imgStr, "BASE64");
//        MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
//        ArrayList<MatchRequest> reqs = new ArrayList<>();
//        reqs.add(req1);
//        reqs.add(req2);
//        JSONObject res = client.match(reqs);
//        System.out.println(res);
        int i = 0;
        String imgStr2;
        for (i=0;i<listItems.size();i++){
            imgStr2 = listItems.get(i).getBase64();
            MatchRequest req1 = new MatchRequest(imgStr, "BASE64");
            MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
            ArrayList<MatchRequest> reqs = new ArrayList<>();
            reqs.add(req1);
            reqs.add(req2);
            JSONObject res = client.match(reqs);
            System.out.println(res);
            if(res != null && !res.toString().equals("")){
                if(res.get("result") != JSONObject.NULL&&res.get("result") != null && !res.get("result").toString().equals("")){
                    JSONObject result = (JSONObject) res.get("result");
                    double score = result.getDouble("score");
                    if(score >= 90){
                        break;
                    }
                }
            }
        }
        System.out.println("local i \n");
        System.out.println(i);
        if(i<listItems.size())
        {
            responseData.put("items",listItems.get(i).getId());
            response.put("code",20000);
            response.put("message","success");
            response.put("data",responseData);
            return response;
        }
        else {
            responseData.put("items","-1");
            response.put("code",20000);
            response.put("message","fail");
            response.put("data",responseData);
            return response;
        }
    }
    @Override
    public HashMap<String, Object> addFace(Face nowbase64) {
        faceRepository.save(nowbase64);
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("message","success");
        response.put("data","add face");
        return response;
    }
}

