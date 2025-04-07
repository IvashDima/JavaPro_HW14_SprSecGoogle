package org.example.springbank.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbank.enums.UserRole;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    private String email;
    private String phone;
    private String address;

    public CustomUser(String login, String password, UserRole role,
                      Client client, String email, String phone, String address) {
        this.login = login;
        this.password = password;
        this.role = role;
//        this.client = client;
        setClient( client);
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    public void setClient(Client client) {
        this.client = client;
        if (client != null && client.getUser() != this) {
            client.setUser(this);
        }
    }
}
