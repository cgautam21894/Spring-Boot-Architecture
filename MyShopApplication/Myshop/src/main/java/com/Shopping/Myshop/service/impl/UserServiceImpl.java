package com.Shopping.Myshop.service.impl;

import com.Shopping.Myshop.dto.AddUserRequest;
import com.Shopping.Myshop.model.UserDetails;
import com.Shopping.Myshop.repository.UserRepository;
import com.Shopping.Myshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public void adduser(AddUserRequest request) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(request.getUserId());
        userDetails.setEmailId(request.getEmailId());
        userDetails.setPassword(request.getPassword());
        userDetails.setPhone(request.getPhone());
        userDetails.setRole(request.getRole());
        userDetails.setUserName(request.getUserName());
        System.out.println(request);
        userRepository.save(userDetails);


    }

    @Override
    public boolean validateUser(AddUserRequest request) {


        Optional<UserDetails> p = userRepository.findByUserName(request.getUserName());
        if (p.isPresent()) {
            UserDetails userDetails = p.get();
            System.out.println(userDetails.getRole()+" "+request.getRole());
            if (userDetails.getPassword().equals(request.getPassword()) && userDetails.getRole().equals(request.getRole())) {
                return true;
            }

            if (userDetails.getPassword().equals(request.getPassword()) && userDetails.getRole().equals(request.getRole())) {
                return true;
            }
        }
        return false;
    }
}
