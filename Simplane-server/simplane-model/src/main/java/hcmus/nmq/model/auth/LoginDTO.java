package hcmus.nmq.model.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 1:38 PM 4/17/2022
 * LeHongQuan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class LoginDTO {
    @NotBlank(message = "Email là bắt buộc")
    private String username;

    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;
}