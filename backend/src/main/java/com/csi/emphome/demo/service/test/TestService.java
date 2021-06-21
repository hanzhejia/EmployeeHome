package com.csi.emphome.demo.service.test;

import com.csi.emphome.demo.service.test.dto.TestListQuery;
import com.csi.emphome.demo.service.test.dto.TestSearchData;
import com.csi.emphome.demo.service.test.dto.TestTemp;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public interface TestService {
    /**
     * 分页查询全部数据
     * @return /
     */
    HashMap<String, Object> fetchListFunc(TestListQuery data);

    /**
     * 分页搜索全部数据
     * @return /
     */
    HashMap<String, Object> fetchListItemFunc(TestSearchData data);

    /**
     * 创建
     * @return /
     */
    HashMap<String, Object> createListItemFunc(TestTemp data);

    /**
     * 编辑
     * @return /
     */
    HashMap<String, Object> updateListItemFunc(TestTemp data);

    /**
     * 删除
     * @return /
     */
    HashMap<String, Object> deleteListItemFunc(TestTemp data);
}
