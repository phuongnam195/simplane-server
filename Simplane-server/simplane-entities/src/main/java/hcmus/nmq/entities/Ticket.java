package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private String code;
    private Date bookTime;
    private String idTicketClass;
    private String idPassenger;
    private String idUser;
    private String idFlight;
}