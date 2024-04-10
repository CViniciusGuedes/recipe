package com.recipe.service;

import com.recipe.model.Usuario;
import com.recipe.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByEmail(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado com o Email " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();


        //Rever o código caso não dê certo (2:43:00)
        return (UserDetails) new Usuario();
    }
}
