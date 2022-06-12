package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



/**
 * 8:30 PM 6/12/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ticket_class")
public class TicketClass {
    @Id
    private String id;
    private String name;
    private String viName;
    private String enName;
    private Boolean canChange;
    private Double changeFee;
    private Boolean canReturn;
    private Double returnFee;
}