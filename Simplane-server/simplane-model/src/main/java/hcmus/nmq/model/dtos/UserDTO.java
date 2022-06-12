package hcmus.nmq.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 9:25 PM 6/2/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("user_name")
    private String username;
    @JsonProperty("pass_word")
    private String password;
    @JsonProperty("full_name")
    private String fullname;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("role")
    private String role;
}