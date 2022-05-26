package hcmus.nmq.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 10:57 AM 4/17/2022
 * LeHongQuan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListWrapper<T> {
    private long total;

    private long currentPage;

    private long maxResult;

    private long totalPage;

    private List<T> data;
}