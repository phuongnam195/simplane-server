package hcmus.nmq.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 9:09 PM 6/20/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private String id;
    private String flightCode;
    private Date flightDate;
    private PassengerDTO passenger;
    private String ticketClassId;
    private Double price;
    private Date bookedTime;
}