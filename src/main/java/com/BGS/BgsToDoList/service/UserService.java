package com.BGS.BgsToDoList.service;

import com.BGS.BgsToDoList.model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);
    public  boolean checkEmail(String email);



}
