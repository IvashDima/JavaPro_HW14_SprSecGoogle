package org.example.springbank.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "client_name")
    private String name;
    private String surname;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> account = new ArrayList<>();

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY) //cascade = CascadeType.ALL, orphanRemoval = true
    private CustomUser user;

    public Client(){}

    public Client(String name, String surname, String phone, String email){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUser(CustomUser user) {
        this.user = user;
        if (user != null) {
            user.setClient(this);
        }
    }

    public CustomUser getUser() {
        return user;
    }

    @Override
    public String toString(){
        return "Client{" +
//                "id="+id+", " +
                "name='"+name+
                ", " +"surname='"+surname+
//              ", " +"phone='"+phone+
//              ", " +"email='"+email+"'" +
                "}";
    }
}
