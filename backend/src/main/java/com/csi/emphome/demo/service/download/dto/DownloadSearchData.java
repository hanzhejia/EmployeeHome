package com.csi.emphome.demo.service.download.dto;

import java.io.Serializable;

public class DownloadSearchData implements Serializable {
    private String select;
    private String search;
    private DownloadListQuery listQuery;

    public DownloadSearchData() {
    }

    public DownloadSearchData(String select, String search, DownloadListQuery listQuery) {
        this.select = select;
        this.search = search;
        this.listQuery = listQuery;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public DownloadListQuery getListQuery() {
        return listQuery;
    }

    public void setListQuery(DownloadListQuery listQuery) {
        this.listQuery = listQuery;
    }

    @Override
    public String toString() {
        return "DownloadSearchData{" +
                "select='" + select + '\'' +
                ", search='" + search + '\'' +
                ", listQuery=" + listQuery +
                '}';
    }
}
