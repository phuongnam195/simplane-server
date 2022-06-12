package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

/**
 * 11:13 PM 5/29/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class AirportConverter {
    public AirportDTO toDTO(Airport airport){
        return AirportDTO.builder()
                .code(airport.getCode())
                .address(airport.getAddress())
                .name(airport.getName()).build();
    }
}