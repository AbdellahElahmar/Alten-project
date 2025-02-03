package alten_project.alten_project.FavouriteArticle.domain;


import alten_project.alten_project.Account.domain.Account;
import alten_project.alten_project.Product.Domain.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fav_product")
@Data
public class FavArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

}
