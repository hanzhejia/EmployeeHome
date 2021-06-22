package com.csi.emphome.demo.service.dept.dto;

import java.io.Serializable;

public class DeptSearchData implements Serializable {
    private String search;
    private DeptListQuery listQuery;

    public DeptSearchData() {
    }

    public DeptSearchData(String search, DeptListQuery listQuery) {
        this.search = search;
        this.listQuery = listQuery;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public DeptListQuery getListQuery() {
        return listQuery;
    }

    public void setListQuery(DeptListQuery listQuery) {
        this.listQuery = listQuery;
    }

    @Override
    public String toString() {
        return "DeptSearchData{" +
                "search='" + search + '\'' +
                ", listQuery=" + listQuery +
                '}';
    }
}
