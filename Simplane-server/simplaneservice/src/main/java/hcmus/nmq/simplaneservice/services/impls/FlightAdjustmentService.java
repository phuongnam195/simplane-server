package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.FlightAdjustment;
import hcmus.nmq.simplaneservice.services.IFlightAdjustmentService;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 11:18 PM 6/23/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class FlightAdjustmentService extends BaseService implements IFlightAdjustmentService {

    @Override
    public Map<String, Double> getMapAdjustmentByFlightId(String flightId) {
        List<FlightAdjustment> flightAdjustments = flightAdjustmentRepository.findAllByFlightId(flightId);
        return flightAdjustments.stream().collect(Collectors.toMap(FlightAdjustment::getIdTicketClass, FlightAdjustment::getPrice));
    }
}