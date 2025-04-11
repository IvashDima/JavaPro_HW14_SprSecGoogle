package org.example.springbank.services;

import org.example.springbank.dto.CustomUserDTO;
import org.example.springbank.enums.UserRole;
import org.example.springbank.models.Client;
import org.example.springbank.models.CustomUser;
import org.example.springbank.repositories.ClientRepository;
import org.example.springbank.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public UserService(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<CustomUser> user = userRepository.findById(id);
            user.ifPresent(u -> {
                if ( ! DemoDataService.ADMIN_LOGIN.equals(u.getEmail())) {
                    userRepository.deleteById(u.getId());
                }
            });
        });
    }

    @Transactional
    public boolean addUser(String email, String passHash,
                           UserRole role, Client client, //String name, String surname,
                           String name) {
        if (email == null || client == null || userRepository.existsByEmail(email))
            return false;

        System.out.println("CLIENT IN USER CREATION (addUser)!!!"+client);
        CustomUser user = CustomUser.create(email, name, passHash, role, client);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public void updateUser(String email, String name) {
        CustomUser user = userRepository.findByEmail(email);
        if (user == null)
            return;

        user.setEmail(email);
        user.setName(name);

        userRepository.save(user);
    }

    @Transactional
    public void addGoogleUser(CustomUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail()))
            return; // do nothing

        Client client = new Client();
        client.setName(userDTO.getName());
        client.setEmail(userDTO.getEmail());

        clientRepository.save(client);

        System.out.println("CLIENT from GOOGLE CREATION!!!"+client);

        CustomUser user = CustomUser.of(userDTO.getEmail(), userDTO.getName(),
                UserRole.USER, client,  userDTO.getPictureUrl());
        user.setClient(client);
        System.out.println("USER from GOOGLE CREATION!!!"+user);

        client.setUser(user);

        userRepository.save(user);
        System.out.println("User saved to DB: " + user);
    }
}