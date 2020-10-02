package com.Shopping.Myshop.dto;


import lombok.Data;


@Data
public class AddUserRequest {

    private long userId;

    private String userName;

    private String password;

    private String role;

    private String emailId;

    private long phone;

}
