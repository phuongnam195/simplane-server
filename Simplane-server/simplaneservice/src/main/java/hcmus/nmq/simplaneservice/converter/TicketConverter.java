package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.entities.Passenger;
import hcmus.nmq.entities.Ticket;
import hcmus.nmq.model.dtos.TicketDTO;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * 11:28 PM 6/20/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class TicketConverter extends BaseConverter {
    public Ticket fromDTO(TicketDTO ticketDTO) {
        return Ticket.builder()
                .bookedTime(ticketDTO.getBookedTime())
                .code(ticketDTO.getFlightCode())
                .idPassenger(ticketDTO.getPassenger().getId())
                .flightDate(ticketDTO.getFlightDate())
                .idTicketClass(ticketDTO.getTicketClassId())
                .price(ticketDTO.getPrice())
                .idUser(ticketDTO.getIdUser()).build();
    }

    public TicketDTO toDTO(Ticket ticket) {
        TicketDTO ticketDTO = TicketDTO.builder()
                .id(ticket.getId())
                .bookedTime(ticket.getBookedTime())
                .flightCode(ticket.getCode())
                .flightDate(ticket.getFlightDate())
                .price(ticket.getPrice())
                .ticketClassId(ticket.getIdTicketClass())
                .idUser(ticket.getIdUser())
                .build();
        Optional<Passenger> passenger = passengerRepository.findById(ticket.getIdPassenger());
        passenger.ifPresent(p -> {
            ticketDTO.setPassenger(passengerConverter.toDTO(p));
        });
        return ticketDTO;
    }
}