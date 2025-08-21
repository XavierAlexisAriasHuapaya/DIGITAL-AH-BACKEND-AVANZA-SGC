package digital.ah.avanza.sgc.module.master.interfaces;

import java.util.List;

import digital.ah.avanza.sgc.module.master.dto.MasterCatalogAllDTO;
import digital.ah.avanza.sgc.module.master.dto.MasterCatalogOneDTO;

public interface MasterCatalogImplement {

    public List<MasterCatalogAllDTO> getAllCatalogs();

    public MasterCatalogOneDTO findOne(Long id);

}
