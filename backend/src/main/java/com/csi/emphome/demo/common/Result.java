package com.csi.emphome.demo.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 统一通用接口返回封装类
 *
 * @author xiaosongyue
 * @date 2021/01/19 09:26:32
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 后台是否处理成功（状态）
     */
    private boolean state;

    /**
     * 前后端约定的状态码（状态码）
     */
    private int code;

    /**
     * 后台响应的信息（处理信息）
     */
    private String message;

    /**
     * 后台响应的数据（返回数据）
     */
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setState(true);
        result.setCode(1);
        result.setMessage("操作成功");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setState(true);
        result.setCode(1);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static Result success(int code, String message) {
        Result result = new Result();
        result.setState(true);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result success(int code, String message, Object data) {
        Result result = new Result();
        result.setState(true);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setState(false);
        result.setCode(-1);
        result.setMessage("操作失败");
        return result;
    }

    public static Result fail(Object data) {
        Result result = new Result();
        result.setState(false);
        result.setCode(-1);
        result.setMessage("操作失败");
        result.setData(data);
        return result;
    }

    public static Result fail(int code, String message) {
        Result result = new Result();
        result.setState(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result fail(int code, String message, Object data) {
        Result result = new Result();
        result.setState(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

