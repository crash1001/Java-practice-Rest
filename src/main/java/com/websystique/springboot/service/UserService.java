package com.websystique.springboot.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springboot.model.User;
import com.websystique.springboot.repository.UserRepository;
 
 
 
@Service
public class UserService {
    
    @Autowired
   private UserRepository userRepository;
     
 
    public List<User> findAll() {
	return (List<User>) userRepository.findAll();
    }

   
    public User findOne(Long id) {
	return userRepository.findOne(id);
    }

    
    public boolean exists(Long id) {
	return userRepository.exists(id);
    }

    
    public void delete(User user) {
	userRepository.delete(user);
	
    }

    
    public void deleteAll() {
	userRepository.deleteAll();
	
    }

    
    public void save(User user) {
	userRepository.save(user);
	
    }
}
//// 
////    private static List<User> populateDummyUsers(){
////        List<User> users = new ArrayList<User>();
////        users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
////        users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
////        users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
////        users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
////        return users;
////    }
// 
//}