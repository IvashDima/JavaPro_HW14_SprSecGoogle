package org.example.springbank.config;

import org.example.springbank.dto.CustomUserDTO;
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

    private final GeneralService generalService;

    public AuthHandler(GeneralService generalService) {
        this.generalService = generalService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
        OAuth2User user = token.getPrincipal();

        Map<String, Object> attributes = user.getAttributes();

        CustomUserDTO userDTO = CustomUserDTO.of(
                (String) attributes.get("email"),
                (String) attributes.get("name"),
                (String) attributes.get("picture")
        );

        generalService.addGoogleUser(userDTO);

        httpServletResponse.sendRedirect("/");
    }
}
