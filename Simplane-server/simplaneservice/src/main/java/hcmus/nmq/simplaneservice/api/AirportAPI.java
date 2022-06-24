package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.model.dtos.FlightDTO;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.model.wrapper.ObjectResponseWrapper;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 11:35 AM 5/28/2022
 * LeHongQuan
 */
@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.AIRPORT_SERVICE_URL)
@Tag(name = "Airport", description = "Airport API")
public class AirportAPI extends BaseAPI {

    @Operation(summary = "Chi tiết sân bay bằng code")
    @GetMapping()
    public Airport getAirportByCode(@RequestParam(value = "code") String code) {
        Airport airport = airportRepository.findByCode(code);
        if (airport == null) {
            throw new SimplaneServiceException("Không tồn tại sân bay!");
        }
        return airport;
    }

    @Operation(summary = "Chi tiết sân bay bằng id")
    @GetMapping(value = "/{id}")
    public Airport getAirportById(@PathVariable(value = "id") String id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (!airport.isPresent()) {
            throw new SimplaneServiceException("Không tồn tại sân bay!");
        }
        return airport.get();
    }

    @Operation(summary = "Danh sách sân bay")
    @GetMapping(value = "/list")
    public ListWrapper<Airport> getAirports() {
        List<Airport> airports = airportRepository.findAll();
        return ListWrapper.<Airport>builder()
                .data(airports)
                .build();
    }

    @RequiredHeaderToken
    @Operation(summary = "Tạo sân bay")
    @PostMapping
    public AirportDTO createAirport(@RequestBody AirportDTO airport) {
        validateCreate(airport);
        isAdmin();
        String id = airportService.saveAirport(airport);
        AirportDTO airportDTO = AirportDTO.builder()
                .name(airport.getName())
                .address(airport.getAddress())
                .code(airport.getCode()).build();
        return airportDTO;
    }

    @RequiredHeaderToken
    @Operation(summary = "Xóa sân bay bằng code")
    @DeleteMapping()
    public ObjectResponseWrapper deleteAirportByCode(@RequestParam(value = "code") String code) {
        isAdmin();
        Airport airport = airportRepository.findByCode(code);
        airportRepository.delete(airport);
        return ObjectResponseWrapper.builder().data("delete").statusCode(200).build();
    }

    public void validateCreate(AirportDTO airport) {
        if (airport.getAddress().isBlankOrNull()) {
            throw new SimplaneServiceException("Địa chỉ không được để trống!");
        }
        if (airport.getCode().isBlankOrNull()) {
            throw new SimplaneServiceException("Code không được để trống!");
        }
        if (airport.getName().isBlankOrNull()) {
            throw new SimplaneServiceException("Tên không được để trống!");
        }
        Airport airportCheck = airportRepository.findByCode(airport.getCode());
        if (airportCheck != null) {
            throw new SimplaneServiceException("Sân bay đã tồn tại mời kiểm tra lại!");
        }
    }
}