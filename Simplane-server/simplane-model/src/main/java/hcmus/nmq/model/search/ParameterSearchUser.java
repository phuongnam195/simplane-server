package hcmus.nmq.model.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 10:30 PM 6/23/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterSearchUser {
    private String id;
    private String username;
    private Long startIndex;
    private Integer maxResult;
    private Boolean isSearchStaff;
}