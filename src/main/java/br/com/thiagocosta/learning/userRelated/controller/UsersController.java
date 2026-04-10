package br.com.thiagocosta.learning.userRelated.controller;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.thiagocosta.learning.userRelated.model.UserModel;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.thiagocosta.learning.userRelated.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


// @Controller Usado para aplicações MVC tradicionais (que retornam Views/HTML como Thymeleaf ou JSP).
@RestController // Usado para APIs REST (que retornam JSON ou XML). Ele é, na verdade, uma combinação de @Controller + @ResponseBody
@RequestMapping("/users")
public class UsersController {
    
    @Autowired //Vai gerenciar todo ciclo de vida desse repository(instanciar)
    private IUserRepository userRepository;

    /*
        Método de acesso do HTTP
        GET - Buscar uma informação
        POST - Adicionar um dado/informação
        PUT - Alterar um dado/info
        DELETE - Remover um dado
        PATCH - Alterar somente uma parte da info/dado
    */
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUserName(userModel.getUserName());

        if(user != null){
            System.out.println("User already found!");
            //Mensagem de erro
            // Status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        var encryptedPassoword = BCrypt.withDefaults().
        hashToString(12/*"Força, mesmo valor presente na documentação" */, userModel.getPassword().toCharArray());
        
        userModel.setPassword(encryptedPassoword);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("/listAll")
    public List<UserModel> getUsersList() {
        var users = this.userRepository.findAll();

        return users;
    }
    

}
