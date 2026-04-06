package br.com.thiagocosta.learning.userRelated.controller;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.thiagocosta.learning.userRelated.model.UserModel;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.thiagocosta.learning.userRelated.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

// @Controller Usado para aplicações MVC tradicionais (que retornam Views/HTML como Thymeleaf ou JSP).
@RestController // Usado para APIs REST (que retornam JSON ou XML). Ele é, na verdade, uma combinação de @Controller + @ResponseBody
@RequestMapping("/users")
public class ApiController {
    
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
    public UserModel createUser(@RequestBody UserModel userModel){
        var userCreated = this.userRepository.save(userModel);
        return userCreated;
    }

}
