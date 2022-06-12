package hcmus.nmq.model.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 11:54 PM 6/12/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterSearchFlight {
    private String flightId;
    private Date fromDate;
    private Date toDate;
    private String fromAirportCode;
    private String toAirportCode;
    private Long startIndex;
    private Integer maxResult;
}