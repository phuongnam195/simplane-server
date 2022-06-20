package hcmus.nmq.simplaneservice.services;

import hcmus.nmq.entities.FlightAttribute;

import java.util.Map;

public interface IFlightAttrService {
    Map<String,Double> getMapAttrByFlightId(String flightId,String type);

    Map<String, FlightAttribute> getMapTypeAttrByFlightId(String flightId, String type);
}
