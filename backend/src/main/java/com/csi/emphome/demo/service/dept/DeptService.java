package com.csi.emphome.demo.service.dept;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface DeptService {
    HashMap<String, Object> fetchListFunc();
}
