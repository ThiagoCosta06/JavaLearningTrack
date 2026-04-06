package br.com.thiagocosta.learning.userRelated.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thiagocosta.learning.userRelated.model.UserModel;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel/*Qual a entidade que este repositorio está representando*/, UUID/*qual o tipo de id que ela usa */>{
    
}
