package digital.ah.avanza.sgc.module.branch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BranchUpdateDTO {

    private Long id;

    private Long currency;

    private String name;

    private String address;

    private String city;

    private String phone;

    private String image;
}
