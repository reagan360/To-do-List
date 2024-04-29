package com.BGS.BgsToDoList.controller;

import com.BGS.BgsToDoList.model.Task;
import com.BGS.BgsToDoList.service.TaskService;
import org.springframework.ui.Model;
import com.BGS.BgsToDoList.model.UserDtls;
import com.BGS.BgsToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email=p.getName();
        UserDtls user= userRepo.findByEmail(email);
        m.addAttribute("user", user);
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView, Principal principal) {
        String email = principal.getName(); // Get the email of the logged-in user
        UserDtls user = userRepo.findByEmail(email); // Retrieve the user details based on the email

        // Get the user's ID
        Long userId = (long) user.getId();

        // Retrieve the todo list for the user
        List<Task> todoList = taskService.getUserTodoList(userId);

        // Add the todo list to the model
        modelAndView.addObject("taskItems", todoList);

        // Return the view name and model
        modelAndView.setViewName("user/home");

        return modelAndView;
    }


}
