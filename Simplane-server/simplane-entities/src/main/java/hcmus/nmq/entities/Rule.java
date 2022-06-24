package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 3:25 PM 6/24/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "rule")
public class Rule {
    @Id
    private String id;
    private double minFlightDuration; // default = 25 minutes
    private double maxMiddleAirport; // default = 3
    private double minStopDuration; // default = 5 minutes
    private double maxStopDuration; // default = 25 minutes
    private double latestTimeBooking; // default = 720 minutes
    private double latestTimeCancelBooking; // default = 1440 minutes
}