package alten_project.alten_project.FavouriteArticle.repository;

import alten_project.alten_project.FavouriteArticle.domain.FavArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavArticleRepository extends JpaRepository<FavArticle, Long> {

    List<FavArticle> findAllByAccountId(Long id);

    boolean existsByAccountIdAndProductId(Long accountId, Long productId);
}
