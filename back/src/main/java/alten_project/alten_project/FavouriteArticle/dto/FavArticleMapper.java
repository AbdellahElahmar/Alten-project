package alten_project.alten_project.FavouriteArticle.dto;


import alten_project.alten_project.FavouriteArticle.domain.FavArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface FavArticleMapper {

    FavArticleMapper INSTANCE = Mappers.getMapper(FavArticleMapper.class);

    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    FavArticleDto map(FavArticle favArticle);

}
