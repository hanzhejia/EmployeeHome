package com.csi.emphome.demo.service.download.dto;

import java.io.Serializable;

public class DownloadListQuery implements Serializable {
    private int page;
    private int limit;

    public DownloadListQuery() {
    }

    public DownloadListQuery(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "TestListQuery{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
