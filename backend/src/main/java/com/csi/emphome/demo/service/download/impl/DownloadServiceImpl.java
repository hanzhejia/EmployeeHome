package com.csi.emphome.demo.service.download.impl;

import com.csi.emphome.demo.config.FileProperties;
import com.csi.emphome.demo.domain.download.DownloadItem;
import com.csi.emphome.demo.repository.download.DownloadRepository;
import com.csi.emphome.demo.service.download.DownloadService;
import com.csi.emphome.demo.service.download.dto.DownloadListQuery;
import com.csi.emphome.demo.service.download.dto.DownloadSearchData;
import com.csi.emphome.demo.service.download.dto.DownloadTemp;
import com.csi.emphome.demo.utils.FileUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService {
    private final DownloadRepository downloadRepository;
    private final FileProperties properties;

    public DownloadServiceImpl(DownloadRepository downloadRepository, FileProperties properties) {
        this.downloadRepository = downloadRepository;
        this.properties = properties;
    }

    @Override
    public HashMap<String, Object> fetchListFunc(DownloadListQuery data) {
        HashMap<String, Object> responseData = new HashMap<>();
        List<DownloadItem> list = downloadRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
        responseData.put("total", downloadRepository.count());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    public static List<DownloadItem> splicePage(List<DownloadItem> list, Integer pageNum, Integer pageSize) {
        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }

        Integer count = list.size(); //记录总数
        Integer pageCount = 0; //页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引

        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        return list.subList(fromIndex, toIndex);
    }

    @Override
    public HashMap<String, Object> fetchListItemFunc(DownloadSearchData data) {
        HashMap<String, Object> responseData = new HashMap<>();

        if(data.getSearch().equals("")){
            return fetchListFunc(data.getListQuery());
        }
        List<DownloadItem> listAll;
        System.out.println(data.getSelect());
        switch (data.getSelect()) {
            case "2": {
                listAll = downloadRepository.findAllByCreateBy(data.getSearch());
                break;
            }
            case "3": {
                listAll = downloadRepository.findAllByDetail(data.getSearch());
                break;
            }
            default: {
                listAll = downloadRepository.findAllByRealName(data.getSearch());
                break;
            }
        }
        List<DownloadItem> list;
        if(listAll.size()>0){
            list = splicePage(listAll, data.getListQuery().getPage(), data.getListQuery().getLimit());
        }else {
            list = listAll;
        }

        responseData.put("total",listAll.size());
        responseData.put("items",list);

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    @Override
    public HashMap<String, Object> createListItemFunc(DownloadTemp data) {
        int resCode = 20001;
        String resData = "failed";

        DownloadItem tag_item = downloadRepository.findByStorageId(data.getStorageId());
        if (tag_item == null){
            DownloadItem temp_item = new DownloadItem();
            temp_item.setStorageId(data.getStorageId());
            temp_item.setRealName(data.getRealName());
            temp_item.setName(data.getName());
            temp_item.setCreateTime(data.getCreateTimeByTS());
            temp_item.setCreateBy(data.getCreateBy());
            temp_item.setDetail(data.getDetail());
            downloadRepository.save(temp_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> updateListItemFunc(DownloadTemp data) {
        int resCode = 20001;
        String resData = "failed";

        DownloadItem tag_item = downloadRepository.findByStorageId(data.getStorageId());
        if (tag_item != null){
            tag_item.setStorageId(data.getStorageId());
            tag_item.setRealName(data.getRealName());
            tag_item.setName(data.getName());
            tag_item.setCreateTime(data.getCreateTimeByTS());
            tag_item.setCreateBy(data.getCreateBy());
            tag_item.setDetail(data.getDetail());
            downloadRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    @Override
    public HashMap<String, Object> deleteListFunc(List<DownloadTemp> data) {
        HashMap<String, Object> response = new HashMap<>();

        for(int i=0; i<data.size(); i++){
            DownloadItem tag_item = downloadRepository.findByStorageId(data.get(i).getStorageId());
            if (tag_item != null){
                FileUtil.del(tag_item.getPath());
                downloadRepository.delete(tag_item);
            }else {
                response.put("code", 20001);
                response.put("data", "failed");
                return response;
            }
        }
        response.put("code", 20000);
        response.put("data", "success");
        return response;
    }

    @Override
    public HashMap<String, Object> uploadFileFunc(DownloadTemp data, MultipartFile multipartFile) {
        HashMap<String, Object> response = new HashMap<>();

        FileUtil.checkSize(properties.getMaxSize(), multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, properties.getPath().getPath() + type +  File.separator);

        DownloadItem tag_item = downloadRepository.findByRealName(file.getName());
        if (tag_item == null) {
            DownloadItem temp_item = new DownloadItem();
            temp_item.setStorageId(data.getStorageId());
            temp_item.setRealName(file.getName());
            temp_item.setName(data.getName());
            temp_item.setSuffix(suffix);
            temp_item.setPath(file.getPath());
            temp_item.setType(type);
            temp_item.setSize(FileUtil.getSize(multipartFile.getSize()));
            temp_item.setCreateBy(data.getCreateBy());
            temp_item.setCreateTime(data.getCreateTimeByTS());
            temp_item.setDetail(data.getDetail());
            System.out.println(temp_item);
            downloadRepository.save(temp_item);
            response.put("code",20000);
            response.put("data",temp_item);
        }else {
            response.put("code",20001);
            response.put("data","failed");
        }
        return response;
    }
}
