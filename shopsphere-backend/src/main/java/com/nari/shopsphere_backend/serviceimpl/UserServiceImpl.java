package com.nari.shopsphere_backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.dto.RegisterRequest;
import com.nari.shopsphere_backend.entity.User;
import com.nari.shopsphere_backend.repository.UserRepository;
import com.nari.shopsphere_backend.service.UserService;

@Service
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    // REGISTER USER
    @Override
    public User registerUser(
            RegisterRequest request) {

        
        // CHECK EMAIL EXISTS
        if (userRepository.findByEmail(
                request.getEmail()).isPresent()) {

            throw new RuntimeException(
                    "Email Already Exists");
        }

        
        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        
        // ENCRYPT PASSWORD
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(request.getRole());

        return userRepository.save(user);
    }
    @Override
    public User save(User user) {

        return userRepository.save(user);
    }
    
    // FIND USER BY EMAIL
    @Override
    public User findByEmail(
            String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"));
    }
    
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"));
    }


    @Override
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}