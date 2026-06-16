package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.models.Role;
import com.example.demo.models.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;

    public AuthService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public AuthResponse signup(SignUpRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Utilisateur user = new Utilisateur();
        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setRole(Role.USER);
        user.setActive(true);

        Utilisateur savedUser = utilisateurRepository.save(user);

        return mapToAuthResponse(savedUser);
    }

    public AuthResponse signin(SignInRequest request) {
        Optional<Utilisateur> userOpt = utilisateurRepository.findByEmail(request.getEmail());
        
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        Utilisateur user = userOpt.get();

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!user.isActive()) {
            throw new RuntimeException("User account is inactive");
        }

        return mapToAuthResponse(user);
    }

    private AuthResponse mapToAuthResponse(Utilisateur user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setNom(user.getNom());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setActive(user.isActive());
        return response;
    }
}
