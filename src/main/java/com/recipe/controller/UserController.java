package com.recipe.controller;

import com.recipe.model.Usuario;
import com.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/usuarios")
    public Usuario createUser(@RequestBody Usuario usuario) throws Exception {

        Usuario isExist = userRepository.findByEmail(usuario.getEmail());
        if(isExist != null){
            throw new Exception("Esse E-mail já está em uso");
        }
        Usuario savedUser = userRepository.save(usuario);
        return savedUser;
    }

    @DeleteMapping("/usuarios/")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        userRepository.deleteById(userId);
        return "Usuario deletado com sucesso!";
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsers(){
        List<Usuario> usuarios = userRepository.findAll();
        return usuarios;
    }

    //@GetMapping("usuarios/{id}")
}
