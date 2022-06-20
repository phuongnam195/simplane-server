package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.entities.Ticket;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface ITicketRepositoryCustom {
    ListWrapper<Ticket> searchTicket(ParameterSearchTicket parameterSearchTicket);
}
