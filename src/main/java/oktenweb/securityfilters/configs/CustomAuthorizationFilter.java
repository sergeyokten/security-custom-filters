package oktenweb.securityfilters.configs;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
// chech if came with token then find and authorize user
public class CustomAuthorizationFilter extends BasicAuthenticationFilter  {
    public CustomAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String dirtyToken = request.getHeader("token");
        System.out.println(dirtyToken+ " token");
        UsernamePasswordAuthenticationToken authenticationToken = null;
        if (StringUtils.isEmpty(dirtyToken) || !dirtyToken.startsWith("yes")) {
            chain.doFilter(request, response);
            return;
        } else {
            String clearToken = dirtyToken.replace("yes ", "");
            String username = clearToken.replace("name=", "");
            // найти юзера по имени ... сформировать объект индентификации
            authenticationToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }
}
