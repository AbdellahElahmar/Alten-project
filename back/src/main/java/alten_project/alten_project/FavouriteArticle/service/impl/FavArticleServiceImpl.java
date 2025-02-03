package alten_project.alten_project.FavouriteArticle.service.impl;

import alten_project.alten_project.Account.repository.AccountRepository;
import alten_project.alten_project.FavouriteArticle.domain.FavArticle;
import alten_project.alten_project.FavouriteArticle.dto.FavArticleDto;
import alten_project.alten_project.FavouriteArticle.dto.FavArticleMapper;
import alten_project.alten_project.FavouriteArticle.repository.FavArticleRepository;
import alten_project.alten_project.FavouriteArticle.service.FavArticleService;
import alten_project.alten_project.FavouriteArticle.web.request.FavArticleRequest;
import alten_project.alten_project.Product.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavArticleServiceImpl implements FavArticleService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final FavArticleRepository favArticleRepository;
    private final FavArticleMapper favArticleMapper;


    @Override
    public void addToFav(FavArticleRequest favArticleRequest) {
        boolean exists = favArticleRepository.existsByAccountIdAndProductId(
                favArticleRequest.getAccountId(),
                favArticleRequest.getProductId()
        );

        if (exists) {
            throw new IllegalStateException("Cet article est déjà dans vos favoris.");
        }

        FavArticle favArticle = new FavArticle();
        favArticle.setAccount(accountRepository.getOne(favArticleRequest.getAccountId()));
        favArticle.setProduct(productRepository.getOne(favArticleRequest.getProductId()));
        favArticleRepository.save(favArticle);
    }

    @Override
    public void removeFav(Long id) {
        FavArticle favArticle = favArticleRepository.getOne(id);
        favArticleRepository.delete(favArticle);

    }

    @Override
    public List<FavArticleDto> getListeFavArticleByAccount(Long id) {
        return favArticleRepository.findAllByAccountId(id).stream().map(favArticleMapper::map).collect(Collectors.toList());
    }
}
