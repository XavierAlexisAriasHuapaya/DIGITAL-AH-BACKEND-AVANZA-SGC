package digital.ah.avanza.sgc.module.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.service.dto.ServiceCreateDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceOneDTO;
import digital.ah.avanza.sgc.module.service.dto.ServicePaginationDTO;
import digital.ah.avanza.sgc.module.service.dto.ServiceUpdateDTO;
import digital.ah.avanza.sgc.module.service.entity.ServiceEntity;
import digital.ah.avanza.sgc.module.service.interfaces.ServiceImplement;
import digital.ah.avanza.sgc.module.service.repository.ServiceRepository;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServiceService implements ServiceImplement {

    private final ServiceRepository serviceRepository;

    private final CompanyService companyService;

    @Transactional
    @Override
    public String create(ServiceCreateDTO service) {
        CompanyOneDTO companyDTO = this.companyService.findOne(service.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        if (this.serviceRepository.existsByCompanyIdAndName(service.getCompany(), service.getName())) {
            throw new MessageException("Service already exists");
        }

        ServiceEntity serviceEntity = ServiceEntity.builder()
                .company(companyEntity)
                .internal_code(service.getInternal_code())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .warrantyMonths(service.getWarrantyMonths())
                .build();
        serviceEntity = serviceRepository.save(serviceEntity);
        return serviceEntity.getId() != 0 ? "Successfully created" : "Error creating service";
    }

    @Transactional
    @Override
    public String update(ServiceUpdateDTO service) {
        Optional<ServiceEntity> serviceOpt = this.serviceRepository.findById(service.getId());
        if (!serviceOpt.isPresent()) {
            throw new MessageException("Service not found");
        }
        ServiceEntity serviceEntity = serviceOpt.get();
        if (!serviceEntity.getName().equals(service.getName())) {
            if (this.serviceRepository.existsByCompanyIdAndName(serviceEntity.getCompany().getId(),
                    service.getName())) {
                throw new MessageException("Service already exists");
            }
        }
        serviceEntity = ServiceEntity.builder()
                .id(serviceEntity.getId())
                .company(serviceEntity.getCompany())
                .internal_code(service.getInternal_code())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .warrantyMonths(service.getWarrantyMonths())
                .build();
        serviceEntity = serviceRepository.save(serviceEntity);
        return serviceEntity.getId() != 0 ? "Successfully updated" : "Error updating service";
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceOneDTO findOne(Long id) {
        Optional<ServiceEntity> serviceOpt = this.serviceRepository.findById(id);
        if (!serviceOpt.isPresent()) {
            throw new MessageException("Service not found");
        }
        ServiceEntity serviceEntity = serviceOpt.get();
        return new ServiceOneDTO(serviceEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<ServicePaginationDTO> pagination(Pageable pageable, Long companyId, String search) {
        Page<ServiceEntity> servicePage = this.serviceRepository.findAll(pageable);
        List<ServicePaginationDTO> serviceDTOs = servicePage.getContent().stream()
                .map(ServicePaginationDTO::new).toList();
        return new PageDTO<>(serviceDTOs, servicePage.getNumber(), servicePage.getSize(),
                servicePage.getTotalElements());
    }

}
