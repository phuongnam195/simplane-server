package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.entities.Flight;
import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.dtos.input.FlightModel;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.converter.FlightConverter;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.until.FlightUtils;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 8:20 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.FLIGHT_SERVICE_URL)
@Tag(name = "Flight", description = "Flight API")
public class FlightAPI extends BaseAPI {
    @Operation(summary = "Chi tiết chuyến bay bằng id")
    @GetMapping(value = "/{id}")
    public FlightDTO getFlightById(@PathVariable(value = "id") String id) {
        FlightProfile flightProfile = flightService.getFlightProfileById(id);
        if (flightProfile == null) {
            throw new SimplaneServiceException(id + " không tồn tại! Vui lòng kiểm tra lại!");
        }
        return flightConverter.toDTO(flightProfile);
    }

    @RequiredHeaderToken
    @Operation(summary = "tạo chuyến bay bằng ")
    @PostMapping(value = "/")
    public FlightDTO createFlight(@RequestBody FlightModel flightModel) {
        validateCreateFlight(flightModel);

        FlightProfile flight = flightConverter.fromModel(flightModel);
        String id = flightService.saveFlight(flight);
        FlightProfile flightProfile = flightService.getFlightProfileById(id);
        return flightConverter.toDTO(flightProfile);
    }

    public void validateCreateFlight(FlightModel flightModel) {
        if (flightModel.getSeatAmount() == null) {
            throw new SimplaneServiceException("Seat Amount không được bỏ trống");
        }
        if (flightModel.getBookedAmount() == null) {
            throw new SimplaneServiceException("Booked Amount không được bỏ trống");
        }
        if (flightModel.getFromAirportCode().isBlankOrNull()) {
            throw new SimplaneServiceException("From Airport Code không được bỏ trống");
        }
        if (flightModel.getToAirportCode().isBlankOrNull()) {
            throw new SimplaneServiceException("To Airport Code không được bỏ trống");
        }
        if (flightModel.getDateTime() == null) {
            throw new SimplaneServiceException("DateTime không được bỏ trống");
        }
        if (flightModel.getDuration() == null) {
            throw new SimplaneServiceException("Duration không được bỏ trống");
        }
        if (flightModel.getMiddleAirportCodes() != null && flightModel.getMiddleAirportCodes().size() > 0) {
            List<String> airportCodes = flightModel.getMiddleAirportCodes();
            for (String airportCode : airportCodes) {
                Airport airport = airportRepository.findByCode(airportCode);
                if (airport == null) {
                    throw new SimplaneServiceException(airportCode + " không được tồn tại!");
                }
            }
        }
    }
}