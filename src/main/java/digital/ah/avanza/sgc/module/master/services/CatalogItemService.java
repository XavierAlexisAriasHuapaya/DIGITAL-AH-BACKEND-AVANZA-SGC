package digital.ah.avanza.sgc.module.master.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import digital.ah.avanza.sgc.module.master.dto.CatalogItemOneDTO;
import digital.ah.avanza.sgc.module.master.entity.CatalogItemEntity;
import digital.ah.avanza.sgc.module.master.interfaces.CatalogItemImplement;
import digital.ah.avanza.sgc.module.master.repository.CatalogItemRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogItemService implements CatalogItemImplement {

    private final CatalogItemRepository catalogItemRepository;

    @Override
    public CatalogItemOneDTO findOne(Long id) {
        Optional<CatalogItemEntity> itemOpt = this.catalogItemRepository.findById(id);
        if (itemOpt.isEmpty()) {
            throw new MessageException("Item not found");
        }
        CatalogItemEntity item = itemOpt.get();
        CatalogItemOneDTO dto = CatalogItemOneDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .status(item.getStatus())
                .build();
        return dto;
    }
}
