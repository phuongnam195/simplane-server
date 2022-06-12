package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 8:26 PM 6/12/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "flight")
public class Flight {
    @Id
    private String id;
    private String code;
    private String fromAirportCode;
    private String toAirportCode;
    private Date dateTime;
    private Double duration;
    private List<String> middleAirportCodes;
    private List<Double> stopDuration;
}