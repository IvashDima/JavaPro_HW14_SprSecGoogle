package org.example.springbank.controllers;

import org.example.springbank.enums.UserRole;
import org.example.springbank.models.Client;
import org.example.springbank.models.CustomUser;
import org.example.springbank.services.ClientService;
import org.example.springbank.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, ClientService clientService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String index(Model model) {
        User user = getCurrentUser();

        String email = user.getUsername();
        CustomUser dbUser = userService.findByEmail(email);
        if (dbUser == null) throw new UsernameNotFoundException("User not found with email: " + email);

        model.addAttribute("email", email); //jstl
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("admin", isAdmin(user));
        model.addAttribute("name", dbUser.getName());

        Client client = dbUser.getClient();
        if (client == null) throw new IllegalStateException("Client not associated with user: " + email);

        model.addAttribute("clientid", client.getId());
        model.addAttribute("phone", client.getPhone());
        model.addAttribute("address", client.getAddress());

        return "index";
    }

    @GetMapping("/userprofile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            String email = user.getUsername();
            CustomUser dbUser = userService.findByEmail(email);

            if (dbUser != null) {
                model.addAttribute("email", dbUser.getEmail());
                model.addAttribute("roles", user.getAuthorities());
                model.addAttribute("admin", isAdmin(user));
                model.addAttribute("name", dbUser.getName());

                Client client = dbUser.getClient();
                if (client != null){
                    model.addAttribute("clientid", client.getId());
                    model.addAttribute("phone", client.getPhone());
                    model.addAttribute("address", client.getAddress());

                } else{
                    throw new IllegalStateException("Client not associated with user: " + email);
                }
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }

        } else if (principal instanceof DefaultOidcUser) {
            DefaultOidcUser oAuth2User = (DefaultOidcUser) principal;
            String email = (String) oAuth2User.getAttributes().get("email");
            CustomUser dbUser = userService.findByEmail(email);

            List<GrantedAuthority> roles = oAuth2User.getAuthorities().stream()
                    .filter(authority -> authority.getAuthority().startsWith("ROLE_"))
                    .collect(Collectors.toList());

            if (dbUser != null) {
                model.addAttribute("email", dbUser.getEmail());
                model.addAttribute("roles", roles);
                model.addAttribute("admin", isAdmin(oAuth2User));
                model.addAttribute("name", dbUser.getName());

                Client client = dbUser.getClient();
                if (client != null){
                    model.addAttribute("clientid", client.getId());
                    model.addAttribute("phone", client.getPhone());
                    model.addAttribute("address", client.getAddress());

                } else{
                    throw new IllegalStateException("Client not associated with user: " + email);
                }
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
        } else {
            throw new IllegalStateException();
        }
        return "userprofile";
    }

//    @GetMapping("/user")
//    public CustomUserDTO user(OAuth2AuthenticationToken auth) {
//        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
//
//        String email = (String) attrs.get("email");
//        String name = (String) attrs.get("name");
//        System.out.println("LOGGED IN SUCCESS USER: " + name);
//        String pictureUrl = (String) attrs.get("picture");
//
//        return CustomUserDTO.of(email, name, pictureUrl);
//    }

    @PostMapping(value = "/update")
    public String update(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String address) {
        User user = getCurrentUser();

        String email = user.getUsername();
        userService.updateUser(email, name);

        return "redirect:/";
    }

    @PostMapping(value = "/newuser")
    public String update(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String address,
                         Model model) {
        String passHash = passwordEncoder.encode(password);

        //if (password.length() < 8)
        //    return "error";

        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPhone(phone);
        client.setPhone(address);

        if ( ! userService.addUser(email, passHash, UserRole.USER, client, name)) {
            model.addAttribute("exists", true);
            model.addAttribute("email", email);
            return "register";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "toDelete[]", required = false) List<Long> ids,
                         Model model) {
        userService.deleteUsers(ids);
        model.addAttribute("users", userService.getAllUsers());

        return "admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // SpEL !!!
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = getCurrentUser();

        model.addAttribute("email", user.getUsername());
        return "unauthorized";
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private boolean isAdmin(User user) {
//        Collection<GrantedAuthority> roles = user.getAuthorities();
//
//        for (GrantedAuthority auth : roles) {
//            if ("ROLE_ADMIN".equals(auth.getAuthority()))
//                return true;
//        }
//        return false;
        return user.getAuthorities().stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
    }

    private boolean isAdmin(DefaultOidcUser oidcUser) {
//        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) oidcUser.getAuthorities();
//
//        for (GrantedAuthority auth : roles) {
//            if ("ROLE_ADMIN".equals(auth.getAuthority()))
//                return true;
//        }
//        return false;
        return oidcUser.getAuthorities().stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
    }
}
