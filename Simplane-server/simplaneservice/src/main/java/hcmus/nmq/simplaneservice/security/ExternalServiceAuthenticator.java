package hcmus.nmq.simplaneservice.security;

import hcmus.nmq.entities.User;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.jwt.JwtTokenProvider;
import hcmus.nmq.simplaneservice.repositories.IUserRepository;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

/**
 * 3:58 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class ExternalServiceAuthenticator implements IExternalServiceAuthenticator {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public AuthenticationWithToken authenticate(String token) {
        AuthenticationWithToken authenticationWithToken;
        String username = null;
        try {
            username = JwtTokenProvider.getUserNameFromJwt(token);
        } catch (Exception e) {
            throw new SimplaneServiceException("Token không hợp lệ");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new SimplaneServiceException("Token không hợp lệ");
        }

        authenticationWithToken = new AuthenticationWithToken(user, null, null);
        return authenticationWithToken;
    }
}