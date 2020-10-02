package com.Shopping.Myshop.dto;


import lombok.Data;

@Data
public class MyResponse {

    public String message = "Ok";
    public String status = "Success";
    public Object response;

    public static MyResponse getMyResponseObject() {
        return new MyResponse();
    }

}
