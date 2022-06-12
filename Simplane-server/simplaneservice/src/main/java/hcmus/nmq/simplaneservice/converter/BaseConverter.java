package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.simplaneservice.repositories.IAirportRepository;
import hcmus.nmq.simplaneservice.repositories.IFlightRepository;
import hcmus.nmq.simplaneservice.repositories.ISequenceNumberRepository;
import hcmus.nmq.simplaneservice.repositories.IUserRepository;
import hcmus.nmq.simplaneservice.services.IAirportService;
import hcmus.nmq.simplaneservice.services.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * 10:24 PM 6/12/2022
 * LeHongQuan
 */

public abstract class BaseConverter {

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
    protected IFlightRepository flightRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected IFlightService flightService;

    @Autowired
    protected AirportConverter airportConverter;
}