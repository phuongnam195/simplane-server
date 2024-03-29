package hcmus.nmq.model.dtos.input;


import com.fasterxml.jackson.annotation.JsonFormat;
import hcmus.nmq.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 8:49 PM 6/12/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightModel {
    private String id;
    private String fromAirportCode;
    private String toAirportCode;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date dateTime;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Double duration;
    private Map<String, Double> seatAmount;
    private Map<String, Double> bookedAmount;
    private Map<String, Double> ticketClassPrice;
    private List<String> middleAirportCodes;
    private List<Double> stopDuration;
}