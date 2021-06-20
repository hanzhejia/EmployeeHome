package com.csi.emphome.demo.service.face;

import com.csi.emphome.demo.domain.test.test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FaceService {
    List<test> list();
}
