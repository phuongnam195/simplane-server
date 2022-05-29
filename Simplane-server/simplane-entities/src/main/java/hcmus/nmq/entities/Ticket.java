package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * 11:31 AM 5/28/2022
 * LeHongQuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;
    private String enName;
    private String viName;
    private boolean canChange;
    private BigDecimal changeFee;
    private boolean canReturn;
    private BigDecimal returnFee;
}