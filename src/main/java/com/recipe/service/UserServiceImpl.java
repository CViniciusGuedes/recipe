package com.recipe.service;

import com.recipe.model.Usuario;
import com.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario findUserById(Long userId) throws Exception {
        Optional<Usuario> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("Usuário não encontrado com o id " + userId);
    }
}
