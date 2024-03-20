package com.recipe.repository;

import com.recipe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByEmail(String email);

}
