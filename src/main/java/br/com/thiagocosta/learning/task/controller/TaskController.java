package br.com.thiagocosta.learning.task.controller;

import br.com.thiagocosta.learning.task.model.TaskModel;
import br.com.thiagocosta.learning.task.repository.ITaskRepository;
import br.com.thiagocosta.learning.utils.Utils;

import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        taskModel.setIdUser((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getBeginDate()) || currentDate.isAfter(taskModel.getEndDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Begin/End date must be after the current date");
        }

        if(taskModel.getBeginDate().isAfter(taskModel.getEndDate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Begin date must be before the current date");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/List")
    public List<TaskModel> ListTask(HttpServletRequest request){
        var taskList = taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));

        return taskList;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable UUID id, @RequestBody TaskModel taskModel, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task not found");
        }

        if(!task.getIdUser().equals(request.getAttribute("idUser"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user does not have right to edit this task");
        }

        Utils.copyNonNullProperties(taskModel, task);
        
        return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(taskModel));
    }
}
