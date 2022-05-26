package hcmus.nmq.simplaneservice.security;

import hcmus.nmq.utils.Constants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 9:56 PM 5/26/2022
 * LeHongQuan
 */

public class AuthenticationFilter extends GenericFilterBean {
    private final String HTTP_REQUEST_HEADER_NAME;
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager, String requestHeaderName) {
        this.authenticationManager = authenticationManager;
        this.HTTP_REQUEST_HEADER_NAME = requestHeaderName;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        System.out.println("[Request] " + httpRequest.getRemoteAddr() + " - " + httpRequest.getMethod() + " - " + httpRequest.getRequestURI());

        Optional<String> token = Optional.ofNullable(httpRequest.getHeader(Constants.AUTHORIZATION));

        try {
            processHeaderAuthentication(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        }
    }

    private void processHeaderAuthentication(Optional<String> token) {
        if (token.equals(Optional.empty()) || !token.get().equals(Constants.AUTHORIZATION_HEADER)) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }

    }
}