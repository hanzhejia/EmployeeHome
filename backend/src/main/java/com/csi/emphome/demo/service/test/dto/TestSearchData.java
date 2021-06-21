package com.csi.emphome.demo.service.test.dto;

import java.io.Serializable;

public class TestSearchData implements Serializable {
    private String search;
    private TestListQuery listQuery;

    public TestSearchData() {
    }

    public TestSearchData(String search, TestListQuery listQuery) {
        this.search = search;
        this.listQuery = listQuery;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public TestListQuery getListQuery() {
        return listQuery;
    }

    public void setListQuery(TestListQuery listQuery) {
        this.listQuery = listQuery;
    }

    @Override
    public String toString() {
        return "TestSearchData{" +
                "search='" + search + '\'' +
                ", listQuery=" + listQuery +
                '}';
    }
}
