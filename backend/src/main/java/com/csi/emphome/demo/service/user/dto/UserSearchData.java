package com.csi.emphome.demo.service.user.dto;

import com.csi.emphome.demo.service.user.dto.UserListQuery;

import java.io.Serializable;

public class UserSearchData implements Serializable  {
    private String search;
    private UserListQuery listQuery;

    public UserSearchData() {
    }

    public UserSearchData(String search, UserListQuery listQuery) {
        this.search = search;
        this.listQuery = listQuery;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UserListQuery getListQuery() {
        return listQuery;
    }

    public void setListQuery(UserListQuery listQuery) {
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