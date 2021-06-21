package com.csi.emphome.demo.repository.download;

import com.csi.emphome.demo.domain.download.DownloadItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DownloadRepository extends JpaRepository<DownloadItem,Integer>, JpaSpecificationExecutor<DownloadItem> {
    DownloadItem findByStorageId(int storageId);
    DownloadItem findByRealName(String RealName);
    List<DownloadItem> findAllByName(String name);
    List<DownloadItem> findAllByCreateBy(String createBy);
}
