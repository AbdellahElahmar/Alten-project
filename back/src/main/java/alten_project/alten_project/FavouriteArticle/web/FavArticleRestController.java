package alten_project.alten_project.FavouriteArticle.web;


import alten_project.alten_project.FavouriteArticle.dto.FavArticleDto;
import alten_project.alten_project.FavouriteArticle.service.FavArticleService;
import alten_project.alten_project.FavouriteArticle.web.request.FavArticleRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FavArticleRestController {

    private final FavArticleService favArticleService;

    @PostMapping("favourite/add")
    public  ResponseEntity<?> addProductToFav(@RequestBody FavArticleRequest request) {
        try {
            favArticleService.addToFav(request);
            return ResponseEntity.ok("Article ajouté aux favoris avec succès.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout aux favoris.");
        }
    }

    @PostMapping("favourites/{id}")
    public ResponseEntity<List<FavArticleDto>> getListeFavArticleByAccount(@PathVariable Long id) {
        return ResponseEntity.ok(this.favArticleService.getListeFavArticleByAccount(id));
    }
    @PostMapping("favourite/remove/{id}")
    public void removeFav(@PathVariable Long id) {
        this.favArticleService.removeFav(id);
    }
}
