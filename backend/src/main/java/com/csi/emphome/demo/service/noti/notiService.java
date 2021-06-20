package com.csi.emphome.demo.service.noti;

import com.csi.emphome.demo.domain.notice.noti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface notiService {
    List<noti> list();
}
