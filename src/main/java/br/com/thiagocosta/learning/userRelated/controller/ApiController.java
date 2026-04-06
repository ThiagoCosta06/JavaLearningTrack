package br.com.thiagocosta.learning.userRelated.controller;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.thiagocosta.learning.userRelated.model.UserModel;
import org.springframework.web.bind.annotation.RequestBody;

// @Controller Usado para aplicações MVC tradicionais (que retornam Views/HTML como Thymeleaf ou JSP).
@RestController // Usado para APIs REST (que retornam JSON ou XML). Ele é, na verdade, uma combinação de @Controller + @ResponseBody
@RequestMapping("/users")
public class ApiController {
    /*
        Método de acesso do HTTP
        GET - Buscar uma informação
        POST - Adicionar um dado/informação
        PUT - Alterar um dado/info
        DELETE - Remover um dado
        PATCH - Alterar somente uma parte da info/dado
    */
    @PostMapping("/create")
    public void createUser(@RequestBody UserModel userModel){
        System.out.println(userModel.getUserName());
    }

}
