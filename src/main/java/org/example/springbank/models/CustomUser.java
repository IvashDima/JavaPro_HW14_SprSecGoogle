package org.example.springbank.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbank.dto.CustomUserDTO;
import org.example.springbank.enums.UserRegisterType;
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
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserRegisterType type;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    private String pictureUrl;

    public CustomUser() {};

    public CustomUser(String email, String name, String password, UserRole role,
                      UserRegisterType type, Client client) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.type = type;
        setClient(client);
    }

    public CustomUser(String email, String name, UserRole role,
                      UserRegisterType type, Client client, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.type = type;
        setClient(client);
        this.pictureUrl = pictureUrl;
    }

    public static CustomUser of(String email, String name, UserRole role,
                                UserRegisterType type, Client client, String pictureUrl) {
        return new CustomUser(email, name, role, type, client, pictureUrl);
    }
    public CustomUserDTO toDTO() {
        return CustomUserDTO.of(email, name, pictureUrl);
    }

    public static CustomUser fromDTO(CustomUserDTO customUserDTO) {
        return CustomUser.of(customUserDTO.getEmail(), customUserDTO.getName(),
                null, null, null, customUserDTO.getPictureUrl());
    }

    public static CustomUser create(String email, String name, String password, UserRole role,
                                    UserRegisterType type, Client client) {
        return new CustomUser(email, name, password, role,type, client);
    }

    public void setClient(Client client) {
        this.client = client;
        if (client != null && client.getUser() != this) {
            client.setUser(this);
        }
    }
}
