package br.com.thiagocosta.learning.task.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Entity(name = "tb_task")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id; 
    
    private String description;
    
    @Column(length = 50)
    private String title;
    
    private LocalDateTime beginDate;
    
    private LocalDateTime endDate;
    
    private String priority;
    
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("The title field have a limit of 50 chars");
        }
        this.title = title;
    }
}
