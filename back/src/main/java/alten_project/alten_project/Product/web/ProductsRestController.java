package alten_project.alten_project.Product.web;

import alten_project.alten_project.Product.Domain.Product;
import alten_project.alten_project.Product.Dto.ProductDto;
import alten_project.alten_project.Product.Service.ProductService;
import alten_project.alten_project.Product.web.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Log4j2
public class ProductsRestController {

    private final ProductService productService;


    @PostMapping("products")
    public void createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
    }
    @GetMapping("products")
    public Page<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "") String q,
            Pageable pageable) {
        log.debug("query " + q + pageable);
        return productService.getListeProducts(q, pageable);
    }
    @GetMapping("products/notp")
    public List<ProductDto> getAllProductsNotPaginated(
            @RequestParam(required = false, defaultValue = "") String q) {
        return productService.getListeProduitsNotPaginated(q);
    }


    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PatchMapping("products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        productService.updateProduct(id, request);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
