package hcmus.nmq.simplaneservice.services;

import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchFlight;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.wrapper.ListWrapper;

public interface ITicketService {
    String saveTicket(TicketDTO ticketModel);

    ListWrapper<TicketDTO> searchTicketDTOS(ParameterSearchTicket parameterSearchTicket);
}
