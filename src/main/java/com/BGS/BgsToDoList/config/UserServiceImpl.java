package com.BGS.BgsToDoList.config;

import com.BGS.BgsToDoList.model.UserDtls;
import com.BGS.BgsToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;




@Configuration
public class UserServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDtls> user = Optional.ofNullable(userRepo.findByEmail(username));
        return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
    }
}