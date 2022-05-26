package hcmus.nmq.simplaneservice.configs;


import hcmus.nmq.simplaneservice.security.AuthenticationFilter;
import hcmus.nmq.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 9:53 PM 5/26/2022
 * LeHongQuan
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .addFilterBefore(
                        new AuthenticationFilter(authenticationManager(), Constants.AUTHORIZATION_HEADER),
                        BasicAuthenticationFilter.class
                );
    }
}