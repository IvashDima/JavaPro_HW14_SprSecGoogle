package org.example.springbank.enums;

public enum UserRegisterType {
    FORM, GOOGLE;

    @Override
    public String toString() {
        return name();
    }
}
