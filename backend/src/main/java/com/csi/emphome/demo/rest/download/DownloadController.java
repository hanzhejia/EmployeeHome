package com.csi.emphome.demo.rest.download;

import com.csi.emphome.demo.service.download.DownloadService;
import com.csi.emphome.demo.service.download.dto.DownloadListQuery;
import com.csi.emphome.demo.service.download.dto.DownloadSearchData;
import com.csi.emphome.demo.service.download.dto.DownloadTemp;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/vue-admin-template/download")
public class DownloadController {
    private final DownloadService downloadService;

    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @CrossOrigin
    @PostMapping(value = "/list")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody DownloadListQuery data) {
        return downloadService.fetchListFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/listItem")
    @ResponseBody
    public HashMap<String, Object> fetchList(@RequestBody DownloadSearchData data) {
        return downloadService.fetchListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    @ResponseBody
    public HashMap<String, Object> createListItem(@RequestBody DownloadTemp data) {
        return downloadService.createListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseBody
    public HashMap<String, Object> updateListItem(@RequestBody DownloadTemp data) {
        return downloadService.updateListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/delete")
    @ResponseBody
    public HashMap<String, Object> deleteListItem(@RequestBody DownloadTemp data) {
        return downloadService.deleteListItemFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/download")
    @ResponseBody
    public HashMap<String, Object> downloadFile(@RequestBody DownloadTemp data) {
        return downloadService.downloadFileFunc(data);
    }

    @CrossOrigin
    @PostMapping(value = "/upload")
    @ResponseBody
    public HashMap<String, Object> uploadFile(DownloadTemp data, @RequestParam("file") MultipartFile file) {
        return downloadService.uploadFileFunc(data, file);
    }
}
