package br.com.thiagocosta.learning.userRelated.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thiagocosta.learning.userRelated.model.UserModel;
import java.util.UUID;
import java.util.List;


public interface IUserRepository extends JpaRepository<UserModel/*Qual a entidade que este repositorio está representando*/, UUID/*qual o tipo de id que ela usa */>{
    
    UserModel findByUserName(String userName);//metodo findBy que ira buscar fazer um select no db pelo username

}
