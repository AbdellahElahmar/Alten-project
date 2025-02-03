package alten_project.alten_project.Product.Service;

import alten_project.alten_project.Product.Domain.Product;
import alten_project.alten_project.Product.Dto.ProductDto;
import alten_project.alten_project.Product.web.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {

    Page<ProductDto> getListeProducts(String q, Pageable pageable);

    List<ProductDto> getListeProduitsNotPaginated(String q);

    ProductDto getProductById(Long id);

    void createProduct(ProductRequest request);

    void updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
