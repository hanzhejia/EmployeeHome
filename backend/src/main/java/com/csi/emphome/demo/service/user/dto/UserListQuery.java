package com.csi.emphome.demo.service.user.dto;

import java.io.Serializable;

public class UserListQuery implements Serializable {
    private int page;
    private int limit;

    public UserListQuery(){

    }
    public UserListQuery(int page,int limit){
        this.page = page;
        this.limit = limit;
    }
    public int getPage(){
        return page;
    }
    public void setPage(int page){
        this.page = page;
    }
    public int getLimit(){
        return limit;
    }
    public void setLimit(int limit){
        this.limit = limit;
    }
    @Override
    public String toString(){
        return "UserListQuery{"
                +"page="+page+
                ",limit="+limit+
                "}";
    }
}
