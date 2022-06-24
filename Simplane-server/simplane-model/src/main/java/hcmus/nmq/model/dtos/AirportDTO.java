package hcmus.nmq.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 5:40 PM 5/29/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO {
    private String id;
    private String code;
    private String name;
    private String address;
}