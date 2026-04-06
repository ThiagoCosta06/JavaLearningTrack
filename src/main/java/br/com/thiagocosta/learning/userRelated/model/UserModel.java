package br.com.thiagocosta.learning.userRelated.model;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDateTime;
//import lombok.Getter;
//import lombok.Setter;

@Data //define getter e setters para os atributos
//@Getter //define getter para os atributos
//@Setter //define setters para os atributos
@Entity(name = "tb_users") //criação da tabela no banco de dados com o nome de "tb_users"
public class UserModel {
    
    @Id //anotation para chave primaria
    @GeneratedValue(generator = "UUID") //Geração do valor de forma automatica com o gerador do tipo UUID obs: pode ser outros geradores
    private UUID id;

    // @Column(name = "...") para caso eu queira mudar o nome do atributo na tabela ao inves de userName
    // @Column(unique = true) para caso eu queira que o atributo abaixo seja unico no banco de dados
    private String userName;
    private String name;
    private String password;

    @CreationTimestamp //Quando o dado for criado ira gerar automatica a data e horario que foi criado o registro
    private LocalDateTime createdAt;

   /* public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    */

}
