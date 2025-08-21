package digital.ah.avanza.sgc.module.branch.dto;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.branch.entity.BranchEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BranchPaginationDTO {

    private Long id;

    private String currency;

    private String name;

    private String phone;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public BranchPaginationDTO(BranchEntity branch) {
        this.id = branch.getId();
        this.currency = branch.getCurrency().getName();
        this.name = branch.getName();
        this.phone = branch.getPhone();
        this.createdAt = branch.getCreatedAt();
        this.updatedAt = branch.getUpdatedAt();
        this.status = branch.getStatus();
    }

}
