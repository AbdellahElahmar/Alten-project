package alten_project.alten_project.Product.Service.Impl;

import alten_project.alten_project.Product.Domain.Product;
import alten_project.alten_project.Product.Dto.Mapper.ProductMapper;
import alten_project.alten_project.Product.Dto.ProductDto;
import alten_project.alten_project.Product.Repository.ProductRepository;
import alten_project.alten_project.Product.Service.ProductService;
import alten_project.alten_project.Product.web.request.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Page<ProductDto> getListeProducts(String q, Pageable pageable) {
        return productRepository.findAllByNameContaining(q, pageable).map(productMapper::productToProductDto);
    }

    @Override
    public List<ProductDto> getListeProduitsNotPaginated(String q) {
        return productRepository.findAllByNameContaining(q).stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.productToProductDto(productRepository.getOne(id));
    }

    @Override
    public void createProduct(ProductRequest request) {
        Product product = productMapper.productRequestToProduct(request);
        productRepository.save(product);

    }

    @Override
    public void updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.getOne(id);
        product = productMapper.productRequestToProduct(request);
        productRepository.save(product);


    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.getOne(id);
        productRepository.delete(product);

    }
}
