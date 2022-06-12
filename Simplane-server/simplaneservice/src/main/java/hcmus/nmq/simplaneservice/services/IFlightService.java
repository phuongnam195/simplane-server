package hcmus.nmq.simplaneservice.services;

import hcmus.nmq.model.profile.FlightProfile;

public interface IFlightService {
    String saveFlight(FlightProfile flight);

    FlightProfile getFlightProfileById(String id);
}
