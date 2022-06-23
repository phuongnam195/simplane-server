package hcmus.nmq.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 10:23 PM 6/23/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {
    private String id;
    private String username;
    private String fullname;
    private Double totalTicket;
    private Double totalRevenue;
}