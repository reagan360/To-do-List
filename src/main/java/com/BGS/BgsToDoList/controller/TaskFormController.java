package com.BGS.BgsToDoList.controller;

import com.BGS.BgsToDoList.model.UserDtls;
import com.BGS.BgsToDoList.repository.UserRepository;
import com.BGS.BgsToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.BGS.BgsToDoList.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.BGS.BgsToDoList.model.Task;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")

public class TaskFormController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepo;



    @Autowired
    private UserService userService;
    @GetMapping("/create-todo")
    public String showCreateForm(Task taskItem,Model m, Principal p) {

        String email=p.getName();
        UserDtls user= userRepo.findByEmail(email);
        m.addAttribute("user", user);
        return "user/new-todo-item";
    }




    @PostMapping("/todo")

    public String addTaskForUser(@RequestParam("userId") Integer userId,
                                 @RequestParam("description") String description) {
        // Retrieve the user by ID (assuming you have a method to fetch user details by ID)
        UserDtls user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a new Task object
        Task task = new Task();
        task.setDescription(description);
        task.setIsComplete(false); // Set default value for isComplete, if needed
        task.setUser(user); // Set the user for the task

        // Save the task
        taskService.save(task);

        // Redirect back to the user's todo list page
        return "redirect:/user/";
    }
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        Task taskItem = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskItem id: " + id + " not found"));

        taskService.delete(taskItem);
        return "redirect:/user/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Task taskItem = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskItem id: " + id + " not found"));

        model.addAttribute("task", taskItem);
        return "user/edit-todo-item";
    }

    @PostMapping("/edit/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid Task taskItem, BindingResult result, Model model) {

        Task item = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskItem id: " + id + " not found"));

        item.setIsComplete(taskItem.getIsComplete());
        item.setDescription(taskItem.getDescription());

        taskService.save(item);

        return "redirect:/user/";
    }


}