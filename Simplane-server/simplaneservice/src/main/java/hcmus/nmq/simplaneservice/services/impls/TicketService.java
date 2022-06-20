package hcmus.nmq.simplaneservice.services.impls;

import hcmus.nmq.entities.Flight;
import hcmus.nmq.entities.FlightAttribute;
import hcmus.nmq.entities.Passenger;
import hcmus.nmq.entities.Ticket;
import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.model.profile.FlightProfile;
import hcmus.nmq.model.search.ParameterSearchTicket;
import hcmus.nmq.model.wrapper.ListWrapper;
import hcmus.nmq.simplaneservice.converter.TicketConverter;
import hcmus.nmq.simplaneservice.services.ITicketService;
import hcmus.nmq.utils.EnumConst;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 11:22 PM 6/20/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Service
public class TicketService extends BaseService implements ITicketService {

    @Override
    public String saveTicket(TicketDTO ticketModel) {
        Ticket ticket = ticketConverter.fromDTO(ticketModel);
        Passenger passenger = passengerConverter.fromDTO(ticketModel.getPassenger());
        if (passenger.getId().isBlankOrNull()) {
            passenger.setId(sequenceNumberRepository.getSequence(Passenger.class));
        }
        Passenger passengerNew = passengerRepository.save(passenger);
        String idPassenger = passenger.getId();
        ticket.setId(sequenceNumberRepository.getSequence(Ticket.class));
        ticket.setIdPassenger(idPassenger);
        Ticket ticketNew = ticketRepository.save(ticket);
        Flight flight = flightRepository.findByCode(ticket.getCode());
        Map<String, FlightAttribute> mapSeat = flightAttrService.getMapTypeAttrByFlightId(flight.getId(), EnumConst.TypeAttrFlight.SEAT.toString());
        FlightAttribute seatAttr = mapSeat.get(ticket.getIdTicketClass());
        seatAttr.setAmount(seatAttr.getAmount() - 1);
        flightAttrRepository.save(seatAttr);
        Map<String, FlightAttribute> mapBook = flightAttrService.getMapTypeAttrByFlightId(flight.getId(), EnumConst.TypeAttrFlight.BOOK.toString());
        FlightAttribute bookAttr = mapBook.get(ticket.getIdTicketClass());
        bookAttr.setAmount(bookAttr.getAmount() + 1);
        flightAttrRepository.save(bookAttr);
        return ticketNew.getId();
    }

    @Override
    public ListWrapper<TicketDTO> searchTicketDTOS(ParameterSearchTicket parameterSearchTicket) {
        ListWrapper<Ticket> wrapper = ticketRepository.searchTicket(parameterSearchTicket);
        List<Ticket> tickets = new ArrayList<>(wrapper.getData());
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        tickets.forEach(ticket -> {
            ticketDTOS.add(ticketConverter.toDTO(ticket));
        });
        return ListWrapper.<TicketDTO>builder()
                .data(ticketDTOS)
                .build();

    }
}