package org.example.springbank.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomUserPrincipal implements UserDetails, OAuth2User {

    private final CustomUser customUser;
    private final Map<String, Object> attributes;

    public CustomUserPrincipal(CustomUser customUser) {
        this(customUser, null);
    }

    public CustomUserPrincipal(CustomUser customUser, Map<String, Object> attributes) {
        this.customUser = customUser;
        this.attributes = attributes;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return customUser.getAuthorities();
        if (customUser == null || customUser.getRole() == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority(customUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return customUser.getPassword();
    }

    @Override
    public String getUsername() {
        return customUser.getEmail();
    }

    @Override
    public String getName() {
        return customUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
