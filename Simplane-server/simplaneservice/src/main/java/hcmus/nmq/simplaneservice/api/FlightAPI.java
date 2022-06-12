package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.entities.Flight;
import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.dtos.input.FlightModel;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.converter.FlightConverter;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.simplaneservice.until.FlightUtils;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
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

    @Operation(summary = "Lấy danh sách chuyến bay ")
    @GetMapping(value = "/")
    public ListWrapper<FlightDTO> getFlights(@RequestParam(value = "id", required = false) String id,
                                             @RequestParam(value = "fromDate", required = false) Long fromDate,
                                             @RequestParam(value = "toDate", required = false) Long toDate,
                                             @RequestParam(value = "fromAirportCode", required = false) String fromAirportCode,
                                             @RequestParam(value = "toAirportCode", required = false) String toAirportCode,
                                             @RequestParam(value = "currentPage", required = false) @Min(value = 1, message = "currentPage phải lớn hơn 0") @Parameter(description = "Default: 1") Integer currentPage,
                                             @RequestParam(value = "maxResult", required = false) @Min(value = 1, message = "maxResult phải lớn hơn 0") @Max(value = 50, message = "maxResult phải bé hơn 50") @Parameter(description = "Default: 20; Size range: 1-50") Integer maxResult) {

        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (maxResult == null || maxResult == 0) {
            maxResult = 20;
        }
        Long startIndex = (long) ((currentPage - 1) * maxResult);
        ParameterSearchFlight parameterSearchFlight = new ParameterSearchFlight();
        if (null != fromDate) {
            parameterSearchFlight.setFromDate(new Date(fromDate));
        }
        if (null != toDate) {
            parameterSearchFlight.setToDate(new Date(toDate));
        }
        parameterSearchFlight.setFromAirportCode(fromAirportCode);
        parameterSearchFlight.setToAirportCode(toAirportCode);
        parameterSearchFlight.setStartIndex(startIndex);
        parameterSearchFlight.setMaxResult(maxResult);

        ListWrapper<FlightProfile> listWrapper = flightService.searchFlightProfiles(parameterSearchFlight);
        List<FlightProfile> flightProfiles = listWrapper.getData();
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flightProfiles.forEach(flightProfile -> {
            flightDTOS.add(flightConverter.toDTO(flightProfile));
        });
        return ListWrapper.<FlightDTO>builder()
                .currentPage(currentPage)
                .maxResult(maxResult)
                .totalPage(listWrapper.getTotalPage())
                .total(listWrapper.getTotal())
                .data(flightDTOS)
                .build();
    }

    @RequiredHeaderToken
    @Operation(summary = "Tạo chuyến bay ")
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