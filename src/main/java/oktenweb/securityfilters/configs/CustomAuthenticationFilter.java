package oktenweb.securityfilters.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import oktenweb.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// if user without token (first attempt to login)
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/loginURL","POST"));
        this.authenticationManager = authenticationManager;
    }

    // на основе имеющихся в запросе данных наполнить объект индентификации
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attempt");
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println(user);
//            System.out.println(request.getParameter("username"));
//            System.out.println(request.getParameter("password"));
            System.out.println(user + "USER!!!!!!!!!!!!!");
            if (user.getUsername().equals("user") && user.getPassword().equals("pass")) {

                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getRoles());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println(authResult + "!!!!!!!!!!!!!!!!!");
    }
}
