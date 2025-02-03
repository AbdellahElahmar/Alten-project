package alten_project.alten_project.FavouriteArticle.service;

import alten_project.alten_project.FavouriteArticle.dto.FavArticleDto;
import alten_project.alten_project.FavouriteArticle.web.request.FavArticleRequest;

import java.util.List;

public interface FavArticleService {

    void addToFav(FavArticleRequest favArticleRequest);

    void removeFav(Long id);

    List<FavArticleDto> getListeFavArticleByAccount(Long id);
}
