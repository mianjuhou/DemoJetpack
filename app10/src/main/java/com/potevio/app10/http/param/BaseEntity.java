package com.potevio.app10.http.param;

public class BaseEntity<T> {
    private T data;
    private String status;
    private String v;
    private String errorMsg;

    public BaseEntity() {
    }

    public BaseEntity(T data) {
        this.data = data;
        this.status = "200";
        this.v = "v1.0";
        this.errorMsg = "成功";
    }

    public BaseEntity(T data, String status, String errorMsg) {
        this.data = data;
        this.status = status;
        this.v = "v1.0";
        this.errorMsg = errorMsg;
    }

    public BaseEntity(T data, String status, String v, String errorMsg) {
        this.data = data;
        this.status = status;
        this.v = v;
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "data=" + data +
                ", status='" + status + '\'' +
                ", v='" + v + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}