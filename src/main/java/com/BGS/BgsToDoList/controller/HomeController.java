package com.BGS.BgsToDoList.controller;

import com.BGS.BgsToDoList.model.UserDtls;
import com.BGS.BgsToDoList.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){

//        System.out.println(user);

       boolean f=userService.checkEmail(user.getEmail());

       if(f){
           session.setAttribute("msg", "Email address already exists");
       }else {
           UserDtls userDtls= userService.createUser(user);
           if (userDtls != null){

               session.setAttribute("msg", "Registerd Successfully");
           }else {
               session.setAttribute("msg", "Something Went wrong, Try refreshing the page");
           }

       }

        return "redirect:/register";
    }


}
