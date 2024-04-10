package com.recipe.controller;

import com.recipe.config.JwtProvider;
import com.recipe.model.Usuario;
import com.recipe.repository.UserRepository;
import com.recipe.response.AuthResponse;
import com.recipe.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetails;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody Usuario usuario) throws Exception{

        String email = usuario.getEmail();
        String password = usuario.getPassword();
        String fullName = usuario.getFullName();

        Usuario isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail != null){
            throw new Exception("Email já está em uso com outro usuário");
        }

        Usuario createdUser = new Usuario();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);

        Usuario savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("Registrado com sucesso!");

        return response;
    }
}
