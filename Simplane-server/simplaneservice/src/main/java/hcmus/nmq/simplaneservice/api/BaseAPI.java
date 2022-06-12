package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.simplaneservice.jwt.JwtTokenProvider;
import hcmus.nmq.simplaneservice.repositories.IAirportRepository;
import hcmus.nmq.simplaneservice.repositories.ISequenceNumberRepository;
import hcmus.nmq.simplaneservice.repositories.IUserRepository;
import hcmus.nmq.simplaneservice.services.IAirportService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * 1:39 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public class BaseAPI {
    protected static final Logger logger = Logger.getLogger(BaseAPI.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    //repository
    @Autowired
    protected IUserRepository userRepository;

    @Autowired
    protected IAirportRepository airportRepository;

    //service
    @Autowired
    protected IAirportService airportService;


    @Autowired
    protected ISequenceNumberRepository sequenceNumberRepository;


    @Autowired
    protected PasswordEncoder passwordEncoder;

}