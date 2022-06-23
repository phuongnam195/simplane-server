package hcmus.nmq.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import hcmus.nmq.utils.Constants;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 8:26 PM 6/12/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FlightDTO {
    private String id;
    private String code;
    private AirportDTO fromAirport;
    private AirportDTO toAirport;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date dateTime;
    private Double duration;
    private Map<String,Double> seatAmount;
    private Map<String,Double> bookedAmount;
    private Map<String,Double> ticketClassPrice;
    private List<AirportDTO> middleAirports;
    private List<Double> stopDurations;
}