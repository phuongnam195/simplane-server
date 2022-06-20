package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.Passenger;
import hcmus.nmq.simplaneservice.converter.AirportConverter;
import hcmus.nmq.simplaneservice.converter.FlightConverter;
import hcmus.nmq.simplaneservice.converter.PassengerConverter;
import hcmus.nmq.simplaneservice.converter.TicketConverter;
import hcmus.nmq.simplaneservice.repositories.*;
import hcmus.nmq.simplaneservice.services.IFlightAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * 11:11 PM 5/29/2022
 * LeHongQuan
 */

public abstract class BaseService {
    protected static final Logger logger = Logger.getLogger(BaseService.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    private final HttpHeaders httpHeaders;

    {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    //Repository

    @Autowired
    protected IAirportRepository airportRepository;

    @Autowired
    protected ISequenceNumberRepository sequenceNumberRepository;

    @Autowired
    protected IUserRepository userRepository;
//    @Autowired
//    protected ISequenceNumberRepositoryCustom sequenceNumberRepositoryCustom;

    @Autowired
    protected IFlightRepository flightRepository;

    @Autowired
    protected IFlightAttrRepository flightAttrRepository;

    @Autowired
    protected IPassengerRepository passengerRepository;

    @Autowired
    protected ITicketRepository ticketRepository;


    //Service
    @Autowired
    protected IFlightAttrService flightAttrService;


    //Converter
    @Autowired
    protected FlightConverter flightConverter;

    @Autowired
    protected PassengerConverter passengerConverter;

    @Autowired
    protected TicketConverter ticketConverter;

    @Autowired
    protected AirportConverter airportConverter;

}