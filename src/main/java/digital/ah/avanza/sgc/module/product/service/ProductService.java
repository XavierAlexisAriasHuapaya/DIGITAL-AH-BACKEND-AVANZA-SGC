package digital.ah.avanza.sgc.module.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import digital.ah.avanza.sgc.module.company.dto.CompanyOneDTO;
import digital.ah.avanza.sgc.module.company.entity.CompanyEntity;
import digital.ah.avanza.sgc.module.company.service.CompanyService;
import digital.ah.avanza.sgc.module.product.dto.ProductCreateDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductOneDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductPaginationDTO;
import digital.ah.avanza.sgc.module.product.dto.ProductUpdateDTO;
import digital.ah.avanza.sgc.module.product.dto.brand.BrandOneDTO;
import digital.ah.avanza.sgc.module.product.dto.category.CategoryOneDTO;
import digital.ah.avanza.sgc.module.product.entity.ProductEntity;
import digital.ah.avanza.sgc.module.product.entity.brand.BrandEntity;
import digital.ah.avanza.sgc.module.product.entity.category.CategoryEntity;
import digital.ah.avanza.sgc.module.product.interfaces.ProductImplement;
import digital.ah.avanza.sgc.module.product.repository.ProductRepository;
import digital.ah.avanza.sgc.module.product.service.brand.BrandService;
import digital.ah.avanza.sgc.module.product.service.category.CategoryService;
import digital.ah.avanza.sgc.utils.exception.MessageException;
import digital.ah.avanza.sgc.utils.model.PageDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService implements ProductImplement {

    private final ProductRepository productRepository;

    private final CompanyService companyService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    @Transactional
    @Override
    public String create(ProductCreateDTO product) {
        CompanyOneDTO companyDTO = this.companyService.findOne(product.getCompany());
        CompanyEntity companyEntity = CompanyEntity
                .builder().id(companyDTO.getId()).build();

        CategoryOneDTO categoryDTO = this.categoryService.findOne(product.getCategory());
        CategoryEntity categoryEntity = CategoryEntity
                .builder().id(categoryDTO.getId()).build();

        BrandOneDTO brandOneDTO = this.brandService.findOne(product.getBrand());
        BrandEntity brandEntity = BrandEntity
                .builder().id(brandOneDTO.getId()).build();

        if (this.productRepository.existsByCompanyIdAndBarCode(product.getCategory(), product.getBarCode())) {
            throw new MessageException("Product already exists: " + product.getBarCode());
        }
        ProductEntity productEntity = ProductEntity.builder()
                .company(companyEntity)
                .category(categoryEntity)
                .brand(brandEntity)
                .internalCode("")
                .barCode(product.getBarCode())
                .name(product.getName())
                .description(product.getDescription())
                .model(product.getModel())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .minStock(product.getMinStock())
                .taxable(product.getTaxable())
                .expirationDate(product.getExpirationDate())
                .warrantyMonths(product.getWarrantyMonths())
                .image(product.getImage())
                .build();
        productEntity = this.productRepository.save(productEntity);
        return productEntity.getId() != 0 ? "Successfully Created" : "Error Creating Product";
    }

    @Transactional
    @Override
    public String update(ProductUpdateDTO product) {

        CategoryOneDTO categoryDTO = this.categoryService.findOne(product.getCategory());
        CategoryEntity categoryEntity = CategoryEntity
                .builder().id(categoryDTO.getId()).build();

        BrandOneDTO brandOneDTO = this.brandService.findOne(product.getBrand());
        BrandEntity brandEntity = BrandEntity
                .builder().id(brandOneDTO.getId()).build();

        Optional<ProductEntity> productOpt = this.productRepository.findById(product.getId());

        if (!productOpt.isPresent()) {
            throw new MessageException("Product not found");
        }
        ProductEntity productEntity = productOpt.get();
        if (!productEntity.getBarCode().equals(product.getBarCode())) {
            if (this.productRepository.existsByCompanyIdAndBarCode(product.getCategory(), product.getBarCode())) {
                throw new MessageException("Product already exists: " + product.getBarCode());
            }
        }

        productEntity = ProductEntity.builder()
                .id(product.getId())
                .company(productEntity.getCompany())
                .category(categoryEntity)
                .brand(brandEntity)
                .internalCode("")
                .barCode(product.getBarCode())
                .name(product.getName())
                .description(product.getDescription())
                .model(product.getModel())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .minStock(product.getMinStock())
                .taxable(product.getTaxable())
                .expirationDate(product.getExpirationDate())
                .warrantyMonths(product.getWarrantyMonths())
                .image(product.getImage())
                .build();
        productEntity = this.productRepository.save(productEntity);
        return productEntity.getId() != 0 ? "Successfully Updated" : "Error Updating Product";
    }

    @Transactional(readOnly = true)
    @Override
    public ProductOneDTO findOne(Long id) {
        Optional<ProductEntity> productOpt = this.productRepository.findById(id);

        if (!productOpt.isPresent()) {
            throw new MessageException("Product not found");
        }

        return new ProductOneDTO(productOpt.get());
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<ProductPaginationDTO> pagination(Pageable pageable, Long companyId) {
        Page<ProductEntity> productPage = this.productRepository.findByCompanyId(pageable, companyId);
        List<ProductPaginationDTO> productList = productPage.getContent().stream()
                .map(product -> new ProductPaginationDTO(product)).toList();
        return new PageDTO<>(productList, productPage.getNumber(), productPage.getSize(),
                productPage.getTotalElements());
    }

}
