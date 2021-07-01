package com.csi.emphome.demo.service.face.impl;
import com.csi.emphome.demo.domain.face.Face;
import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.utils.jwt.JwtUtil;
import com.csi.emphome.demo.repository.face.FaceRepository;
import com.csi.emphome.demo.service.face.FaceService;
import com.csi.emphome.demo.repository.user.UserRepository;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.csi.emphome.demo.service.face.util.BaiduAIPCommon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 人脸识别
 */
@Service
public class FaceServiceImpl implements FaceService {
    private RedisTemplate redisTemplate;
    @Value("${custom.jwt.expire_time}")
    private long expireTime;
    private final FaceRepository faceRepository;
    private final UserRepository userRepository;
    public FaceServiceImpl(RedisTemplate redisTemplate, FaceRepository faceRepository, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.faceRepository = faceRepository;
        this.userRepository = userRepository;
    }
    /**
     * 从数据库中删除
     * @param nowface 人脸数据
     * @return 删除成功
     */
    @Override
    public HashMap<String, Object> delFace(Face nowface){
        faceRepository.delete(nowface);
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("message","face");
        response.put("data","successdel");
        return response;
    };
    /**
     * 从数据库中获取所有人脸数据
     * @return 人脸数据列表
     */
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
    /**
     * 人脸比对
     * 用的是自己的百度账号接入
     * @param nowbase64 前端返回的人脸
     * @return 比对结果
     * @throws JSONException
     */
    @Override
    public HashMap<String, Object> serchFace(Face nowbase64) throws JSONException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List<Face> listItems = faceRepository.findAll();
        AipFace client = new AipFace(BaiduAIPCommon.APP_FACE_ID, BaiduAIPCommon.API_FACE_KEY, BaiduAIPCommon.SECRET_FACE_KEY);
        String imgStr = nowbase64.getBase64();
        int i = 0;
        String imgStr2;
        /**
         * 将传回的现在的base64数据同数据库里相比对
         */
        for (i=0;i<listItems.size();i++){
            imgStr2 = listItems.get(i).getBase64();
            MatchRequest req1 = new MatchRequest(imgStr, "BASE64");
            MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
            ArrayList<MatchRequest> reqs = new ArrayList<>();
            reqs.add(req1);
            reqs.add(req2);
            JSONObject res = client.match(reqs);
            /**
             * 获取人脸比对结果
             */
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
        /**
         * 判断是否在人脸数据库中
         */
        if(i<listItems.size()){
            UserItem uitem =userRepository.findById(listItems.get(i).getId());
            HashMap<String, Object> token = new HashMap<>();
            String token_str = JwtUtil.sign(uitem.getLoginname(), uitem.getPassword());
            redisTemplate.opsForValue().set(token_str,token_str, expireTime*2/100, TimeUnit.SECONDS);
            token.put("token", token_str);
            response.put("code",20000);
            response.put("data",token);
            return response;
        }
        else {
            response.put("code",60204);
            response.put("message", "抱歉，人脸未识别");
            return response;
        }
    }
    /**
     * 从数据库中增加、修改
     * @param nowbase64 人脸数据
     * @return 添加是否成功
     * @throws JSONException
     */
    @Override
    public HashMap<String, Object> addFace(Face nowbase64) throws JSONException {
        AipFace client = new AipFace(BaiduAIPCommon.APP_FACE_ID, BaiduAIPCommon.API_FACE_KEY, BaiduAIPCommon.SECRET_FACE_KEY);
        String imgStr = nowbase64.getBase64();
        String imgStr2 = nowbase64.getBase64();
        MatchRequest req1 = new MatchRequest(imgStr, "BASE64");
        MatchRequest req2 = new MatchRequest(imgStr2, "BASE64");
        ArrayList<MatchRequest> reqs = new ArrayList<>();
        reqs.add(req1);
        reqs.add(req2);
        JSONObject res = client.match(reqs);
        System.out.println("faceres");
        /**
         * 判断是否识别到人脸，未识别到则返回未识别信息
         */
        if(res.get("result") == JSONObject.NULL||res.get("result") == null || res.get("result").toString().equals(""))
        {
            HashMap<String, Object> response = new HashMap<>();
            response.put("code", 20000);
            response.put("message", "fail");
            response.put("data", "no face");
            return response;
        }
        else {
            faceRepository.save(nowbase64);
            HashMap<String, Object> response = new HashMap<>();
            response.put("code", 20000);
            response.put("message", "success");
            response.put("data", "add face");
            return response;
        }
    }
}

