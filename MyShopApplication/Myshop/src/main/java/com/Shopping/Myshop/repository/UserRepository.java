package com.Shopping.Myshop.repository;

import com.Shopping.Myshop.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDetails,Long> {

        Optional<UserDetails> findByUserName(String userName);
}
