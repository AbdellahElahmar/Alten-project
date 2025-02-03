package alten_project.alten_project.Product.Dto.Mapper;


import alten_project.alten_project.Product.Domain.Product;
import alten_project.alten_project.Product.Dto.ProductDto;
import alten_project.alten_project.Product.web.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);

    @Mapping(target = "id", ignore = true )
    Product productRequestToProduct(ProductRequest productRequest);


}
