package com.Shopping.Myshop.service;


import com.Shopping.Myshop.dto.AddUserRequest;

public interface UserService {
    void adduser(AddUserRequest request);

    boolean validateUser(AddUserRequest request);
}
