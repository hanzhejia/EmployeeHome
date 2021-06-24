package com.csi.emphome.demo.service.download;

import com.csi.emphome.demo.service.download.dto.DownloadListQuery;
import com.csi.emphome.demo.service.download.dto.DownloadSearchData;
import com.csi.emphome.demo.service.download.dto.DownloadTemp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public interface DownloadService {
    /**
     * 分页查询全部数据
     * @return /
     */
    HashMap<String, Object> fetchListFunc(DownloadListQuery data);

    /**
     * 分页搜索全部数据
     * @return /
     */
    HashMap<String, Object> fetchListItemFunc(DownloadSearchData data);

    /**
     * 创建
     * @return /
     */
    HashMap<String, Object> createListItemFunc(DownloadTemp data);

    /**
     * 编辑
     * @return /
     */
    HashMap<String, Object> updateListItemFunc(DownloadTemp data);

    /**
     * 删除
     * @return /
     */
    HashMap<String, Object> deleteListFunc(List<DownloadTemp> data);

    HashMap<String, Object> uploadFileFunc(DownloadTemp data, MultipartFile file);
}
