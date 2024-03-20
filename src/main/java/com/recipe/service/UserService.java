package com.recipe.service;

import com.recipe.model.Usuario;

public interface UserService {

    public Usuario findUserById(Long userId) throws Exception;
}
