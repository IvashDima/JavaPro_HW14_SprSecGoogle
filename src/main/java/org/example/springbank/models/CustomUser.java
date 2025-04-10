package org.example.springbank.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbank.dto.CustomUserDTO;
import org.example.springbank.enums.UserRole;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customusers")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; //login

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    private String name;
    private String phone;
    private String address;
    private String pictureUrl;

    public CustomUser() {};

    public CustomUser(String email, String password, UserRole role, Client client, String name,
                      String phone, String address, String pictureUrl) {
        this.email = email;
        this.password = password;
        this.role = role;
        setClient(client);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.pictureUrl = pictureUrl;
    }

    public CustomUser(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static CustomUser of(String email, String name, String pictureUrl) {
        return new CustomUser(email, name, pictureUrl);
    }

    public CustomUserDTO toDTO() {
        return CustomUserDTO.of(email, name, pictureUrl);
    }

    public static CustomUser fromDTO(CustomUserDTO userDTO) {
        return CustomUser.of(userDTO.getEmail(), userDTO.getName(), userDTO.getPictureUrl());
    }

    public static CustomUser create(String email, String password, UserRole role,
                                    Client client, String name,
                                    String phone, String address, String pictureUrl) {
        return new CustomUser(email, password, role, client, name, phone, address, pictureUrl);
    }

    // Упрощенные версии create() можно оставить при желании
    public static CustomUser create(String email, String name, String pictureUrl) {
        return create(email, null, null, null, name, null, null, pictureUrl);
    }

    public static CustomUser create(String email, String password, UserRole role,
                                    Client client, String name) {
        return create(email, password, role, client, name, null, null, null);
    }

    public static CustomUser create(String email, String password, UserRole role,
                                    Client client, String name,
                                    String phone, String address) {
        return create(email, password, role, client, name, phone, address, null);
    }

//    public static CustomUser create(String email, String name, String pictureUrl) {
//        return new CustomUser(email, null, null, null, name, null, null, pictureUrl);
//    }
//
//    public static CustomUser create(String email, String password, UserRole role, Client client, String name) {
//        return new CustomUser(email, password, role, client, name, null, null, null);
//    }
//
//    public static CustomUser create(String email, String password, UserRole role, Client client, String name,
//                                    String phone, String address) {
//        return new CustomUser(email, password, role, client, name, phone, address, null);
//    }
//
//    public static CustomUser create(String email, String password, UserRole role, Client client, String name,
//                                    String phone, String address, String pictureUrl) {
//        return new CustomUser(email, password, role, client, name, phone, address, pictureUrl);
//    }

    public void setClient(Client client) {
        this.client = client;
        if (client != null && client.getUser() != this) {
            client.setUser(this);
        }
    }
}
