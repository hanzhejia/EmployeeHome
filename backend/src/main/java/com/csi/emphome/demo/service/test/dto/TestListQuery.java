package com.csi.emphome.demo.service.test.dto;

import java.io.Serializable;

public class TestListQuery implements Serializable {
    private int page;
    private int limit;

    public TestListQuery() {
    }

    public TestListQuery(int page, int limit) {
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
