package com.csi.emphome.demo.service.face.impl;
import com.csi.emphome.demo.domain.face.Face;
import com.csi.emphome.demo.domain.user.UserItem;
import com.csi.emphome.demo.jwt.JwtUtil;
import com.csi.emphome.demo.repository.face.FaceRepository;
import com.csi.emphome.demo.service.face.FaceService;
import com.csi.emphome.demo.repository.user.UserRepository;
import com.csi.emphome.demo.service.user.dto.UserListQuery;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.csi.emphome.demo.domain.face.Face;
import com.csi.emphome.demo.repository.face.FaceRepository;
import com.csi.emphome.demo.service.face.FaceService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.csi.emphome.demo.service.face.util.BaiduAIPCommon;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Override
    public HashMap<String, Object> delFace(Face nowface){
        faceRepository.delete(nowface);
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("message","face");
        response.put("data","successdel");
        return response;
    };
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
        String imgStr = nowbase64.getBase64();
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

        System.out.println("local i");
        System.out.println(i);
        UserItem uitem =userRepository.findById(listItems.get(i).getId());
        if(uitem!=null && i<listItems.size()){
            HashMap<String, Object> token = new HashMap<>();
            String token_str = JwtUtil.sign(uitem.getLoginname(), uitem.getPassword());
            redisTemplate.opsForValue().set(token_str,token_str, expireTime*2/100, TimeUnit.SECONDS);
            token.put("token", token_str);
            response.put("code",20000);
            response.put("data",token);
            return response;
        }
        response.put("code",60204);
        response.put("message","Account and password are incorrect.");
        return response;
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

