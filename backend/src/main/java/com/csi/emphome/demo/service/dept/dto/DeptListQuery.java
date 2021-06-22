package com.csi.emphome.demo.service.dept.dto;

import java.io.Serializable;

public class DeptListQuery implements Serializable {
    private int page;
    private int limit;

    public DeptListQuery() {
    }

    public DeptListQuery(int page, int limit) {
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
        return "DeptListQuery{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
