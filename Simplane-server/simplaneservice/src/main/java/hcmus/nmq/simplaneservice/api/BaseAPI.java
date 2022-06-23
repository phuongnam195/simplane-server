package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.User;
import hcmus.nmq.simplaneservice.converter.AirportConverter;
import hcmus.nmq.simplaneservice.converter.FlightConverter;
import hcmus.nmq.simplaneservice.converter.TicketConverter;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.jwt.JwtTokenProvider;
import hcmus.nmq.simplaneservice.repositories.*;
import hcmus.nmq.simplaneservice.services.IAirportService;
import hcmus.nmq.simplaneservice.services.IFlightAttrService;
import hcmus.nmq.simplaneservice.services.IFlightService;
import hcmus.nmq.simplaneservice.services.ITicketService;
import hcmus.nmq.simplaneservice.services.impls.UserService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * 1:39 PM 4/17/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
public abstract class BaseAPI {
    protected static final Logger logger = Logger.getLogger(BaseAPI.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    //repository
    @Autowired
    protected IUserRepository userRepository;

    @Autowired
    protected IAirportRepository airportRepository;

    @Autowired
    protected ITicketRepository ticketRepository;

    @Autowired
    protected IFlightAttrRepository flightAttrRepository;

    @Autowired
    protected IFlightAdjustmentRepository flightAdjustmentRepository;

    //service
    @Autowired
    protected IAirportService airportService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ISequenceNumberRepository sequenceNumberRepository;

    @Autowired
    protected IFlightRepository flightRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected IFlightService flightService;

    @Autowired
    protected ITicketService ticketService;

    @Autowired
    protected IFlightAttrService flightAttrService;

    //Converter
    @Autowired
    protected FlightConverter flightConverter;

    @Autowired
    protected AirportConverter airportConverter;

    @Autowired
    protected TicketConverter ticketConverter;

    protected void isAdmin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        if (!user.isAdmin()) {
            throw new SimplaneServiceException("Không đủ quyền! Vui lòng kiểm tra lại!");
        }
    }

}