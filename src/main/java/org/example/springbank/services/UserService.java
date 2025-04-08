package org.example.springbank.services;

import org.example.springbank.enums.UserRole;
import org.example.springbank.models.Client;
import org.example.springbank.models.CustomUser;
import org.example.springbank.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                           String name, String phone,
                           String address) {
        if (userRepository.existsByEmail(email))
            return false;

        System.out.println("CLIENT IN USER CREATION!!!"+client);
        CustomUser user = CustomUser.create(email, passHash, role, client, name, phone, address);

        userRepository.save(user);
        return true;
    }

    @Transactional
    public void updateUser(String email, String name, String phone, String address) {
        CustomUser user = userRepository.findByEmail(email);
        if (user == null)
            return;

        user.setEmail(email);
        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);

        userRepository.save(user);
    }
}
