package com.ecom.service.impl;

import com.ecom.model.Login;
import com.ecom.model.Registration;
import com.ecom.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface , UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails  getallStudent(Login login) throws UsernameNotFoundException
    {
        List<Registration> user = new ArrayList<>();
        userRepository.findAll().forEach(student -> user.add(student));
        boolean f;
        System.out.println(user);
        for(Registration e : user){
            System.out.println(e.getEmail());
            if(e.getEmail().equals(login.getEmail()) ){
                return new User(login.getEmail(), e.getPassword(),
                        new ArrayList<>());
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + login.getEmail());
    }

    public void saveOrUpdate(Registration student)
    {
        userRepository.save(student);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
