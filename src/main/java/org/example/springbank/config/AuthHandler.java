package org.example.springbank.config;

import org.apache.catalina.User;
import org.example.springbank.dto.CustomUserDTO;
import org.example.springbank.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.example.springbank.services.GeneralService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class AuthHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public AuthHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        System.out.println("OAuth2 authentication successful!!!");
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();

        System.out.println("User attributes: " + attributes);

        CustomUserDTO userDTO = CustomUserDTO.of(
                (String) attributes.get("email"),
                (String) attributes.get("name"),
                (String) attributes.get("picture")
        );

        userService.addGoogleUser(userDTO);
        System.out.println("User ADDED!!!");
        httpServletResponse.sendRedirect("/");
    }
}
