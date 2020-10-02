package com.Shopping.Myshop.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "UserDetails")
public class UserDetails {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "phone")
    private long phone;


}
