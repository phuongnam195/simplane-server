package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.FlightAttribute;
import hcmus.nmq.simplaneservice.services.IFlightAttrService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 11:12 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class FlightAttrService extends BaseService implements IFlightAttrService {

    @Override
    public Map<String, Double> getMapAttrByFlightId(String flightId, String type) {
        List<FlightAttribute> flightAttributes = flightAttrRepository.findAllByTypeAndFlightId(type, flightId);
        return flightAttributes.stream().collect(Collectors.toMap(FlightAttribute::getIdTicketClass, FlightAttribute::getAmount));
    }

    @Override
    public Map<String, FlightAttribute> getMapTypeAttrByFlightId(String flightId, String type) {
        List<FlightAttribute> flightAttributes = flightAttrRepository.findAllByTypeAndFlightId(type, flightId);
        return flightAttributes.stream().collect(Collectors.toMap(FlightAttribute::getIdTicketClass, Function.identity()));
    }
}