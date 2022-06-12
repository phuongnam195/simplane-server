package hcmus.nmq.simplaneservice.services;

import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface IFlightService {
    String saveFlight(FlightProfile flight);

    FlightProfile getFlightProfileById(String id);

    ListWrapper<FlightProfile> searchFlightProfiles(ParameterSearchFlight parameterSearchFlight);
}
