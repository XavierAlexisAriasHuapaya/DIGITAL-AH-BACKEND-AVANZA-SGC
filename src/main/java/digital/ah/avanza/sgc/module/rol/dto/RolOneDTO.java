package digital.ah.avanza.sgc.module.rol.dto;

import java.time.ZonedDateTime;

import digital.ah.avanza.sgc.module.rol.entity.RolEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolOneDTO {

    private Long id;

    private String name;

    private String description;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Boolean status;

    public RolOneDTO(RolEntity rol) {
        this.id = rol.getId();
        this.name = rol.getName();
        this.description = rol.getDescription();
        this.createdAt = rol.getCreatedAt();
        this.updatedAt = rol.getUpdatedAt();
        this.status = rol.getStatus();
    }

}
