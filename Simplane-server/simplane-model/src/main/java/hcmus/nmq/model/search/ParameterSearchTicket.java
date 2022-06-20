package hcmus.nmq.model.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 1:08 AM 6/21/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterSearchTicket {
    private String id;
//    private String flightId;
    private Date fromDate;
    private Date toDate;
    private String flightCode;
    private Long startIndex;
    private Integer maxResult;
}