package hcmus.nmq.simplaneservice.services;

import java.util.Map;


public interface IFlightAdjustmentService {
    Map<String,Double> getMapAdjustmentByFlightId(String flightId);
}
