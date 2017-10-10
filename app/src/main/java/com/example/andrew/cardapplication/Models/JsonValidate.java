package com.example.andrew.cardapplication.Models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrew on 10.10.2017.
 */

public class JsonValidate {
    @SerializedName("validate")
    private Boolean validate;

    //common fields
    @SerializedName("object_or_array")
    private String objOrArray;

    //field for info parsing
    @SerializedName("empty")
    private Boolean empty;
    @SerializedName("parse_time_nanoseconds")
    private String parseTime;
    @SerializedName("size")
    private String size;

    //field for error
    @SerializedName("error")
    private String error;
    @SerializedName("error_info")
    private String errorInfo;

    public Boolean getValidate() {
        return validate;
    }
    public String getObjOrArray() {
        return objOrArray;
    }
    public Boolean getEmpty() {
        return empty;
    }
    public String getParseTime() {
        return parseTime;
    }
    public String getSize() {
        return size;
    }
    public String getError() {
        return error;
    }
    public String getErrorInfo() {
        return errorInfo;
    }


    public void setValidate(Boolean validate) {
        this.validate = validate;
    }
    public void setObjOrArray(String objOrArray) {
        this.objOrArray = objOrArray;
    }
    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
    public void setParseTime(String parseTime) {
        this.parseTime = parseTime;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setError(String error) {
        this.error = error;
    }
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
