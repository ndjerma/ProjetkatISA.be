package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.user.UserAlreadyExistsException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.LoginResponseModel;
import com.example.demo.models.LoginUserModel;
import com.example.demo.models.RegisterUserModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserModel signup(RegisterUserModel model) {
        var user = UserMapper.toEntity(model, passwordEncoder);

        var existingUser = userRepository.findByEmail(model.getEmail());    // trazimo korisnika sa istim mejlom
        if (existingUser.isPresent()) {     //ako imamo taj mejl u bazi onda greska izlazi
            throw new UserAlreadyExistsException("User with email " + model.getEmail() + " already exists");
        }
        var savedUser = userRepository.save(user);  //ako nemamo usera takvog u bazi onda ga kreiramo
        return UserMapper.toModel(savedUser);   //prebacujemo entity u model i vracamo u controller
    }

    public LoginResponseModel authenticate(LoginUserModel model) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getEmail(),
                        model.getPassword()
                )
        );  //Kreiramo UsernamePasswordAuthenticationToken (Spring-ov objekat koji sadrži username + password).

        var authenticatedUser = userRepository.findByEmail(model.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User with email " + model.getEmail() + " not found"));

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseModel loginResponse = new LoginResponseModel();
        loginResponse.setToken(jwtToken);

        return loginResponse;
    }
}