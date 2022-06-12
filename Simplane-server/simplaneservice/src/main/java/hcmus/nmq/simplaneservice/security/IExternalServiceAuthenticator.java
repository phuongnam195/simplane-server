package hcmus.nmq.simplaneservice.security;

public interface IExternalServiceAuthenticator {
    AuthenticationWithToken authenticate(String token);
}
