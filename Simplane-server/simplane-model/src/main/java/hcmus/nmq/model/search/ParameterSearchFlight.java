package hcmus.nmq.model.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import hcmus.nmq.utils.Constants;
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
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date fromDate;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private Date toDate;
    private String fromAirportCode;
    private String toAirportCode;
    private Long startIndex;
    private Integer maxResult;
}