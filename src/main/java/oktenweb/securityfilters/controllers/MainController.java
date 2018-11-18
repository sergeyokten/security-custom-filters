package oktenweb.securityfilters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.security.Security;

@RestController
public class MainController {
    @GetMapping("/")
    public String home(Authentication authentication, Principal principal) {

        System.out.println("authentication" + " " + authentication);
        System.out.println("authentication 2" + " " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("principal" + " " + principal);
        return "home";
    }


}
