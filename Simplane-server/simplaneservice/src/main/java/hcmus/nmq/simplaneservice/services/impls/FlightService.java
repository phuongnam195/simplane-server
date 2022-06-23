package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.entities.Flight;
import hcmus.nmq.entities.FlightAdjustment;
import hcmus.nmq.entities.FlightAttribute;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.services.IFlightService;
import hcmus.nmq.simplaneservice.until.FlightUtils;
import hcmus.nmq.utils.EnumConst;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 9:32 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class FlightService extends BaseService implements IFlightService {

    @Override
    public String saveFlight(FlightProfile flight) {
        Flight flightNew = new Flight();
        flightNew.setId(flight.getId());
        if (flight.getId().isBlankOrNull()) {
            String id = sequenceNumberRepository.getSequence(Flight.class);
            flightNew.setId(id);
        }
        flightNew.setMiddleAirportCodes(flight.getMiddleAirportCodes());
        flightNew.setFromAirportCode(flight.getFromAirport().getCode());
        flightNew.setToAirportCode(flight.getToAirport().getCode());
        flightNew.setCode(flight.getCode());
        if (flight.getCode().isBlankOrNull()) {
            flightNew.setCode(FlightUtils.randomCode(flightNew.getId()));
        }
        flightNew.setDuration(flight.getDuration());
        flightNew.setDateTime(flight.getDateTime());
        flightNew.setStopDuration(flight.getStopDurations());

        Map<String, Double> mapSeat = flight.getSeatAmount();
        if (mapSeat != null) {
            for (Map.Entry<String, Double> entry : mapSeat.entrySet()) {
                FlightAttribute seat = new FlightAttribute();
                seat.setType(EnumConst.TypeAttrFlight.SEAT.toString());
                seat.setAmount(entry.getValue());
                seat.setIdTicketClass(entry.getKey());
                seat.setFlightId(flightNew.getId());
                flightAttrRepository.save(seat);
            }
        }
        Map<String, Double> mapBook = flight.getBookedAmount();
        if (mapBook != null) {
            for (Map.Entry<String, Double> entry : mapBook.entrySet()) {
                FlightAttribute book = new FlightAttribute();
                book.setType(EnumConst.TypeAttrFlight.BOOK.toString());
                book.setAmount(entry.getValue());
                book.setIdTicketClass(entry.getKey());
                book.setFlightId(flightNew.getId());
                flightAttrRepository.save(book);
            }
        }
        Map<String,Double> mapIdTicketClassPrice = flight.getTicketClassPrice();
        if(mapIdTicketClassPrice != null){
            for(Map.Entry<String,Double> entry: mapIdTicketClassPrice.entrySet()){
                FlightAdjustment flightAdjustment = new FlightAdjustment();
                flightAdjustment.setFlightId(flightNew.getId());
                flightAdjustment.setIdTicketClass(entry.getKey());
                flightAdjustment.setPrice(entry.getValue());
                flightAdjustmentRepository.save(flightAdjustment);
            }
        }
        Flight flightSave = flightRepository.save(flightNew);
        return flightSave.getId();
    }

    @Override
    public FlightProfile getFlightProfileById(String id) {
        Optional<Flight> flight = flightRepository.findById(id);
        Flight flightFind = flight.get();
        if (flightFind == null) {
            return null;
        }
        FlightProfile flightProfile = buildFlightProfile(flightFind);

        return flightProfile;
    }

    @Override
    public ListWrapper<FlightProfile> searchFlightProfiles(ParameterSearchFlight parameterSearchFlight) {
        ListWrapper<Flight> wrapper = flightRepository.searchFlights(parameterSearchFlight);
        List<Flight> flights = new ArrayList<>(wrapper.getData());
        List<FlightProfile> flightProfiles = new ArrayList<>();
        flights.forEach(flight -> {
            flightProfiles.add(buildFlightProfile(flight));
        });
        return ListWrapper.<FlightProfile>builder()
                .data(flightProfiles)
                .build();
    }

    public FlightProfile buildFlightProfile(Flight flight) {
        FlightProfile flightProfile = new FlightProfile();
        String id = flight.getId();
        flightProfile.setId(flight.getId());

        flightProfile.setCode(flight.getCode());
        flightProfile.setDateTime(flight.getDateTime());
        flightProfile.setDuration(flight.getDuration());
        if (!flight.getFromAirportCode().isBlankOrNull()) {
            Airport airport = airportRepository.findByCode(flight.getFromAirportCode());
            flightProfile.setFromAirport(airport);
        }
        if (!flight.getToAirportCode().isBlankOrNull()) {
            Airport airport = airportRepository.findByCode(flight.getToAirportCode());
            flightProfile.setToAirport(airport);
        }

        List<String> airportMiddleIds = flight.getMiddleAirportCodes();
        if (airportMiddleIds != null && airportMiddleIds.size() != 0) {
            List<Airport> airports = airportRepository.findAllByCode(airportMiddleIds);
            flightProfile.setMiddleAirports(airports);
        }
        flightProfile.setStopDurations(flight.getStopDuration());
        Map<String,Double> mapSeat = flightAttrService.getMapAttrByFlightId(flightProfile.getId(),EnumConst.TypeAttrFlight.SEAT.toString());
        flightProfile.setSeatAmount(mapSeat);

        Map<String,Double> mapBook = flightAttrService.getMapAttrByFlightId(flightProfile.getId(),EnumConst.TypeAttrFlight.BOOK.toString());
        flightProfile.setBookedAmount(mapBook);

        Map<String,Double> mapIdTicketClassPrice = flightAdjustmentService.getMapAdjustmentByFlightId(flightProfile.getId());
        flightProfile.setTicketClassPrice(mapIdTicketClassPrice);

        return flightProfile;

    }
}