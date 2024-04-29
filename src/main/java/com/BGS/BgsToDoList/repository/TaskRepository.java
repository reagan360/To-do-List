// TaskRepository.java
package com.BGS.BgsToDoList.repository;

import com.BGS.BgsToDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long>  {
    List<Task> findByUserId(Long userId);

    void deleteById(Long id);
}

