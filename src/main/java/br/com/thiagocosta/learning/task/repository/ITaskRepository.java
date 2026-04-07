package br.com.thiagocosta.learning.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thiagocosta.learning.task.model.TaskModel;
import java.util.UUID;
import java.util.List;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
    List<TaskModel> findByIdUser(UUID idUser);

}
