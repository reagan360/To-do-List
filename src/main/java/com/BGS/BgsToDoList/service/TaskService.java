// TaskService.java
package com.BGS.BgsToDoList.service;

import com.BGS.BgsToDoList.model.Task;
import com.BGS.BgsToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;




    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task save(Task taskItem) {
        if (taskItem.getId() == null) {
            taskItem.setCreatedAt(Instant.now());
        }
        taskItem.setUpdatedAt(Instant.now());
        return taskRepository.save(taskItem);
    }

    public void delete(Task taskItem) {
        taskRepository.delete(taskItem);
    }




        public List<Task> getUserTodoList(Long userId) {
            return taskRepository.findByUserId(userId);
        }

}
