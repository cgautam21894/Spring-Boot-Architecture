package com.Shopping.Myshop.controller;

import com.Shopping.Myshop.dto.AddUserRequest;
import com.Shopping.Myshop.dto.MyResponse;
import com.Shopping.Myshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping(value = "/User/add")
    public MyResponse addUser(@RequestBody AddUserRequest request) {
        userService.adduser(request);
        MyResponse m = MyResponse.getMyResponseObject();
        m.setMessage("User Created Successfully");
        m.setStatus("Passed");
        m.setResponse(request);
        return m;
    }

    @PostMapping(value = "User/login")
    public MyResponse validateUser(@RequestBody AddUserRequest request) {
        MyResponse m = MyResponse.getMyResponseObject();

        if (userService.validateUser(request)) {
            m.setMessage("User Logged In Successfully");
            m.setStatus("Passed");
        } else {
            m.setMessage("User Logged In Unsuccessful");
            m.setStatus("Failed");
        }
        m.setResponse(request);
        return m;
    }
}
