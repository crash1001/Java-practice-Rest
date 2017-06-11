package com.websystique.springboot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.User;
import com.websystique.springboot.service.UserService;
import com.websystique.springboot.util.CustomErrorType;


@RestController
@RequestMapping("/api")
public class RestApiController {
 
    @Autowired
    private UserService userService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Users---------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
       
        User user = userService.findOne(id);
        if (user == null) {
            
            return new ResponseEntity(new CustomErrorType("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        
 
        if (userService.exists(user.getId())) {
           
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.save(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a User ------------------------------------------------
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        
 
        User currentUser = userService.findOne(id);
 
        if (currentUser == null) {
            
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
    
 
        User user1 = userService.findOne(id);
        if (user1 == null) {
           
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.delete(user1);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
       
 
        userService.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
}