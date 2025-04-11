package org.example.springbank.config;

import org.example.springbank.dto.CustomUserDTO;
import org.example.springbank.enums.UserRegisterType;
import org.example.springbank.models.CustomUser;
import org.example.springbank.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.getOrDefault("email", "");

        System.out.println("User attributes: " + attributes);

        CustomUser user = userService.findByEmail(email);

        if (user == null) {
            CustomUserDTO userDTO = CustomUserDTO.of(
                    (String) attributes.get("email"),
                    (String) attributes.get("name"),
                    (String) attributes.get("picture")
            );

            userService.addGoogleUser(userDTO);
            httpServletResponse.sendRedirect("/");
        } else {
            if (user.getType().equals(UserRegisterType.GOOGLE)) {
                httpServletResponse.sendRedirect("/");
            } else {
                httpServletResponse.sendRedirect("/login?errorEmail");
            }
        }
    }
}
