package digital.ah.avanza.sgc.module.master.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.master.dto.MasterCatalogAllDTO;
import digital.ah.avanza.sgc.module.master.dto.MasterCatalogOneDTO;
import digital.ah.avanza.sgc.module.master.entity.MasterCatalogEntity;
import digital.ah.avanza.sgc.module.master.interfaces.MasterCatalogImplement;
import digital.ah.avanza.sgc.module.master.repository.MasterCatalogRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MasterCatalogService implements MasterCatalogImplement {

    private final MasterCatalogRepository masterCatalogRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MasterCatalogAllDTO> getAllCatalogs() {
        return this.masterCatalogRepository.findAll().stream()
                .map(master -> new MasterCatalogAllDTO(
                        master.getId(),
                        master.getName(),
                        master.getCatalog_items(),
                        master.getStatus()))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MasterCatalogOneDTO findOne(Long id) {
        Optional<MasterCatalogEntity> masterOpt = this.masterCatalogRepository.findById(id);
        if (masterOpt.isEmpty()) {
            throw new MessageException("Catalog not found");
        }
        MasterCatalogEntity entity = masterOpt.get();
        MasterCatalogOneDTO dto = MasterCatalogOneDTO.builder()
                .id(id)
                .name(entity.getName())
                .catalog_items(entity.getCatalog_items())
                .status(entity.getStatus())
                .build();
        return dto;
    }

}
