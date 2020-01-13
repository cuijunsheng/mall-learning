package com.cjs.mall.common;

/**
 * @description: 通用返回对象
 * @author: cuijunsheng
 * @date: 2020-01-13 10:53
 **/
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data){

        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功返回结果
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(String message,T data){

        return new CommonResult<>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String message){

        return new CommonResult<>(ResultCode.FAILED.getCode(),message,null);
    }


    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(){

        return new CommonResult<>(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage(),null);
    }

    /**
     * 参数验证失败返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(){

        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),null);
    }

    /**
     * 参数验证失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(String message){

        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),message,null);
    }

    /**
     * 未登录或token已经过期返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(){

        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(),null);
    }

    /**
     * 未授权返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(){

        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),null);
    }


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
