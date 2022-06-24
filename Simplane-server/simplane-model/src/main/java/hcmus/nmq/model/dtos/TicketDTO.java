package hcmus.nmq.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import hcmus.nmq.utils.Constants;
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
    //    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss.mmmuuuZ")
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date flightDate;
    private PassengerDTO passenger;
    private String ticketClassId;
    private Double price;
    //    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss.mmmuuuZ")
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date bookedTime;
    private String idUser;
}