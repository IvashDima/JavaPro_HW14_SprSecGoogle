package org.example.springbank.dto;

public class CustomUserDTO {
    private final String email;
    private final String name;
    private final String pictureUrl;

    private CustomUserDTO(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static CustomUserDTO of(String email, String name, String pictureUrl) {
        return new CustomUserDTO(email, name, pictureUrl);
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
}