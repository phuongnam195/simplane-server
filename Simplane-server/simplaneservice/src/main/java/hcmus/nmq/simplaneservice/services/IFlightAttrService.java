package hcmus.nmq.simplaneservice.services;

import java.util.Map;

public interface IFlightAttrService {
    Map<String,Double> getMapAttrByFlightId(String flightId,String type);
}
