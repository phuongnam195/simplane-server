package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 10:20 PM 6/12/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "flight_attribute")
public class FlightAttribute {
    @Id
    private String id;
    private String type;
    private String idTicketClass;
    //    private String attrValue;
    private Double amount;
    private String flightId;
}