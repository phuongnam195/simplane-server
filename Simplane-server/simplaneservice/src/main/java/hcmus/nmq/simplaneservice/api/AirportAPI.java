package hcmus.nmq.simplaneservice.api;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.simplaneservice.annotations.swagger.RequiredHeaderToken;
import hcmus.nmq.simplaneservice.handler.SimplaneServiceException;
import hcmus.nmq.utils.Constants;
import hcmus.nmq.utils.Extensions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.*;

/**
 * 11:35 AM 5/28/2022
 * LeHongQuan
 */
@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.AIRPORT_SERVICE_URL)
@Tag(name = "Airport", description = "Airport API")
public class AirportAPI extends BaseAPI {
    @RequiredHeaderToken
    @Operation(summary = "Chi tiết sân bay bằng code")
    @GetMapping(value = "/{code}")
    public Airport getAirportByCode(@PathVariable(value = "code") String code) {
        Airport airport = airportRepository.findByCode(code);
        if(airport == null){
            throw new SimplaneServiceException("Không tồn tại sân bay!");
        }
        return airport;
    }

    @RequiredHeaderToken
    @Operation(summary = "Tạo sân bay")
    @PostMapping
    public AirportDTO createAirport(@RequestBody AirportDTO airport) {
        validateCreate(airport);
        String id = airportService.saveAirport(airport);
        AirportDTO airportDTO = AirportDTO.builder()
                .name(airport.getName())
                .address(airport.getAddress())
                .code(airport.getCode()).build();
        return airportDTO;
    }

    @RequiredHeaderToken
    @Operation(summary = "Xóa sân bay bằng code")
    @DeleteMapping(value = "/{code}")
    public void deleteAirportByCode(@PathVariable(value = "code") String code){
        Airport airport = airportRepository.findByCode(code);
        airportRepository.delete(airport);
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