package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface IFlightRepositoryCustom {
    ListWrapper<Flight> searchProduct(ParameterSearchFlight parameterSearchFlight);
}
