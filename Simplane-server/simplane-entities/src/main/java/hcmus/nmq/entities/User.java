package hcmus.nmq.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 10:32 AM 4/17/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String fullname;
    private String accessToken;
}