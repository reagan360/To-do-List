package com.BGS.BgsToDoList.service;

import com.BGS.BgsToDoList.model.UserDtls;
import com.BGS.BgsToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService") // Specify a unique bean name
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override


    public UserDtls createUser(UserDtls user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        return userRepo.save(user);
    }
    @Override
    public boolean checkEmail(String email) {

        return userRepo.existsByEmail(email);
    }
}
