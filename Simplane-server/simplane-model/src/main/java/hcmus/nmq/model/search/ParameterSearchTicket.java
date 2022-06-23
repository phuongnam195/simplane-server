package hcmus.nmq.model.search;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.mmmuuu'Z'")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.mmmuuu'Z'")
    private Date toDate;
    private String flightCode;
    private Long startIndex;
    private Integer maxResult;
}