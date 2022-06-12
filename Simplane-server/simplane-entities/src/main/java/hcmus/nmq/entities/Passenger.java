package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 8:29 PM 6/12/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "passenger")
public class Passenger {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String email;
    private String phoneNumber;
}