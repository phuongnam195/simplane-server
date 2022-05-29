package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.model.dtos.AirportDTO;
import hcmus.nmq.simplaneservice.services.IAirportService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

/**
 * 11:10 PM 5/29/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class AirportService extends BaseService implements IAirportService {


    @Override
    public String saveAirport(AirportDTO airportDTO) {
        String id = sequenceNumberRepository.getSequence(Airport.class);
        Airport airport = Airport.builder()
                .id(id)
                .name(airportDTO.getName())
                .code(airportDTO.getCode())
                .address(airportDTO.getAddress()).build();
        airportRepository.save(airport);
        return id;
    }
}