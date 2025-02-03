package alten_project.alten_project.Product.Dto.Mapper;

import alten_project.alten_project.Product.Domain.Product;
import alten_project.alten_project.Product.Dto.ProductDto;
import alten_project.alten_project.Product.web.request.ProductRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-02T22:31:25+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setCode( product.getCode() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setImage( product.getImage() );
        productDto.setCategory( product.getCategory() );
        productDto.setPrice( product.getPrice() );
        productDto.setQuantity( product.getQuantity() );
        productDto.setInternalReference( product.getInternalReference() );
        productDto.setShellId( product.getShellId() );
        productDto.setInventoryStatus( product.getInventoryStatus() );
        productDto.setRating( product.getRating() );
        productDto.setCreatedAt( product.getCreatedAt() );
        productDto.setUpdatedAt( product.getUpdatedAt() );

        return productDto;
    }

    @Override
    public Product productRequestToProduct(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product product = new Product();

        product.setCode( productRequest.getCode() );
        product.setName( productRequest.getName() );
        product.setDescription( productRequest.getDescription() );
        product.setImage( productRequest.getImage() );
        product.setCategory( productRequest.getCategory() );
        product.setPrice( productRequest.getPrice() );
        product.setQuantity( productRequest.getQuantity() );
        product.setInternalReference( productRequest.getInternalReference() );
        product.setShellId( productRequest.getShellId() );
        product.setInventoryStatus( productRequest.getInventoryStatus() );
        product.setRating( productRequest.getRating() );
        product.setCreatedAt( productRequest.getCreatedAt() );
        product.setUpdatedAt( productRequest.getUpdatedAt() );

        return product;
    }
}
