package digital.ah.avanza.sgc.utils.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageDTO<T> {

    private List<T> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

}
