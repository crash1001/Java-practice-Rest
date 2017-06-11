package com.websystique.springboot.repository;


import org.springframework.data.repository.CrudRepository;


import com.websystique.springboot.model.User;


public interface UserRepository extends CrudRepository<User, Long>{
    
}
