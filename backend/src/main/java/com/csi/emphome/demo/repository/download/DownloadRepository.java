package com.csi.emphome.demo.repository.download;

import com.csi.emphome.demo.domain.download.DownloadItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

public interface DownloadRepository extends JpaRepository<DownloadItem,Integer>, JpaSpecificationExecutor<DownloadItem> {
    DownloadItem findByStorageId(int storageId);
    DownloadItem findByRealName(String RealName);
    List<DownloadItem> findAllByRealName(String realName);
    List<DownloadItem> findAllByName(String Name);
    List<DownloadItem> findAllByCreateBy(String createBy);
    List<DownloadItem> findAllByDetail(String detail);
}
