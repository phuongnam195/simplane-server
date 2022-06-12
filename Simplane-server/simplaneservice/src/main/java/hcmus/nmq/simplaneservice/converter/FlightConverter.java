package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.entities.Airport;

import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.dtos.input.FlightModel;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 9:06 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class FlightConverter extends BaseConverter {


    public FlightDTO toDTO(FlightProfile flight) {
        if (flight == null) {
            return null;
        }
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setBookedAmount(flight.getBookedAmount());
        flightDTO.setSeatAmount(flight.getSeatAmount());
        flightDTO.setCode(flight.getCode());
        flightDTO.setDateTime(flight.getDateTime());
        flightDTO.setDuration(flight.getDuration());
        if (flight.getFromAirport() != null) {
            flightDTO.setFromAirport(airportConverter.toDTO(flight.getFromAirport()));
        }
        if (flight.getToAirport() != null) {
            flightDTO.setToAirport(airportConverter.toDTO(flight.getToAirport()));
        }
        if (flight.getMiddleAirports() != null && flight.getMiddleAirports().size() != 0) {
            List<AirportDTO> airportDTOS = new ArrayList<>();
            flight.getMiddleAirports().forEach(airport -> {
                airportDTOS.add(airportConverter.toDTO(airport));

            });
            flightDTO.setMiddleAirports(airportDTOS);
        }
        flightDTO.setStopDurations(flight.getStopDurations());
        flightDTO.setSeatAmount(flight.getSeatAmount());
        flightDTO.setBookedAmount(flight.getBookedAmount());
        return flightDTO;
    }

    public FlightProfile fromModel(FlightModel flightModel) {
        if (flightModel == null) {
            return null;
        }
        FlightProfile flight = new FlightProfile();
        if (!flightModel.getFromAirportCode().isBlankOrNull()) {
            Airport airport = airportRepository.findByCode(flightModel.getFromAirportCode());
            flight.setFromAirport(airport);
        }
        if (!flightModel.getToAirportCode().isBlankOrNull()) {
            Airport airport = airportRepository.findByCode(flightModel.getToAirportCode());
            flight.setToAirport(airport);
        }

        flight.setDateTime(flightModel.getDateTime());
        flight.setDuration(flightModel.getDuration());
        List<String> airportMiddleCodes = flightModel.getMiddleAirportCodes();
        flight.setMiddleAirportCodes(airportMiddleCodes);
        if (airportMiddleCodes != null && airportMiddleCodes.size() != 0) {
            List<Airport> airports = airportRepository.findAllByCode(airportMiddleCodes);
            flight.setMiddleAirports(airports);
        }

        if (flightModel.getBookedAmount() != null) {
            flight.setBookedAmount(flightModel.getBookedAmount());
        }
        if (flightModel.getSeatAmount() != null) {
            flight.setSeatAmount(flightModel.getSeatAmount());
        }
        return flight;
    }
}