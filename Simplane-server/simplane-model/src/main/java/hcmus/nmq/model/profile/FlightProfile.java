package hcmus.nmq.model.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import hcmus.nmq.entities.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 9:36 PM 6/12/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightProfile {
    private String id;
    private String code;
    private Airport fromAirport;
    private Airport toAirport;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.mmmuuu'Z'")
    private Date dateTime;
    private Double duration;
    private Map<String, Double> seatAmount;
    private Map<String, Double> bookedAmount;
    private List<String> middleAirportCodes;
    private List<Airport> middleAirports;
    private List<Double> stopDurations;
}