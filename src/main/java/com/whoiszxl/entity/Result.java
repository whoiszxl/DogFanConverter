package com.whoiszxl.entity;

/**
 * @description: 返回结果
 * @author: whoiszxl
 * @create: 2020-03-11
 **/
public class Result {

    private int code;
    private String result;
    private String message;

    public Result(int code, String result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public static Result buildError(String message) {
        return new Result(-1, "", message);
    }

    public static Result buildSuccess(String result, String message) {
        return new Result(0, result, message);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
