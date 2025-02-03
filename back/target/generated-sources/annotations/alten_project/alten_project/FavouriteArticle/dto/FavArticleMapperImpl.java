package alten_project.alten_project.FavouriteArticle.dto;

import alten_project.alten_project.FavouriteArticle.domain.FavArticle;
import alten_project.alten_project.Product.Domain.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-03T01:13:11+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class FavArticleMapperImpl implements FavArticleMapper {

    @Override
    public FavArticleDto map(FavArticle favArticle) {
        if ( favArticle == null ) {
            return null;
        }

        FavArticleDto favArticleDto = new FavArticleDto();

        favArticleDto.setName( favArticleProductName( favArticle ) );
        favArticleDto.setDescription( favArticleProductDescription( favArticle ) );
        favArticleDto.setId( favArticle.getId() );

        return favArticleDto;
    }

    private String favArticleProductName(FavArticle favArticle) {
        if ( favArticle == null ) {
            return null;
        }
        Product product = favArticle.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String favArticleProductDescription(FavArticle favArticle) {
        if ( favArticle == null ) {
            return null;
        }
        Product product = favArticle.getProduct();
        if ( product == null ) {
            return null;
        }
        String description = product.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }
}
