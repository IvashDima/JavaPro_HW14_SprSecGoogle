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
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<CustomUser> user = userRepository.findById(id);
            user.ifPresent(u -> {
                if ( ! DemoDataService.ADMIN_LOGIN.equals(u.getLogin())) {
                    userRepository.deleteById(u.getId());
                }
            });
        });
    }

    @Transactional
    public boolean addUser(String login, String passHash,
                           UserRole role, Client client, //String name, String surname,
                           String email, String phone,
                           String address) {
        if (userRepository.existsByLogin(login))
            return false;

//        Client client = new Client();
//        client.setName(name);
//        client.setSurname(surname);
//        client.setEmail(email);
//        client.setPhone(phone);
//
//        CustomUser user = new CustomUser();
//        user.setLogin(login);
//        user.setPassword(passHash);
//        user.setRole(role);
//        user.setClient(client);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setAddress(address);

//        Client client = new Client(name,surname, email, phone);
        CustomUser user = new CustomUser(login, passHash, role, client, email, phone, address);

        userRepository.save(user);
        return true;
    }

    @Transactional
    public void updateUser(String login, String email, String phone, String address) {
        CustomUser user = userRepository.findByLogin(login);
        if (user == null)
            return;

        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        userRepository.save(user);
    }
}
