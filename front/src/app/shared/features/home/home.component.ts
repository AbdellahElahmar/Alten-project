import {Component, inject, OnInit} from "@angular/core";
import { RouterLink } from "@angular/router";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import {TableModule} from "primeng/table";
import {CurrencyPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {DataViewModule} from "primeng/dataview";
import {ProductsService} from "../../../products/data-access/products.service";
import {DialogModule} from "primeng/dialog";
import {LoginComponent} from "../../../login/login.component";
import {FavorisService} from "../../../favoris/data-access/Favoris.service";
import Swal from 'sweetalert2';
import {AddFavorisRequest} from "../../../favoris/data-access/favoris.model";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
  standalone: true,
  imports: [CardModule, RouterLink, ButtonModule, TableModule, CurrencyPipe, RatingModule, FormsModule, DataViewModule, JsonPipe, NgForOf, DialogModule, NgIf, LoginComponent],
})
export class HomeComponent implements OnInit{
  public readonly appTitle = "ALTEN SHOP";
  private readonly productsService = inject(ProductsService);
  display: boolean = false;
  firstName: string = '';
  username: string = '';
  public searchQuery: string = "";
  isRegistering: boolean = false;
  token: string = ''

  public readonly products = this.productsService.products;
  public readonly totalElements = this.productsService.totalElements
  protected email: string= "" ;
  protected password: string= "";

  constructor(
    private favoritsService: FavorisService
  ) {
  }
  ngOnInit() {
    this.token = JSON.stringify(localStorage.getItem("jwtToken"))
    console.log(this.token)
    localStorage.setItem('cart', JSON.stringify([]))
    this.loadProducts()

  }


  loadProducts() {
    this.productsService.getNotPaginated({
        q: this.searchQuery
      }
    ).subscribe();
  }


  filterProducts() {
    this.loadProducts()
    }

  addToCart(product: any) {
    if(product.quantity > 0) {
      let cart = JSON.parse(localStorage.getItem('cart') || '[]');
      const existingProduct = cart.find((item: any)  => item.id === product.id);
      if (existingProduct) {
        existingProduct.quantity += product.quantity;
      } else {
        cart.push({ ...product });
      }

      localStorage.setItem('cart', JSON.stringify(cart));
      this.updateCartCount();

      Swal.fire({
        title: 'Produit ajouté !',
        text: `${product.name} a été ajouté au panier avec quantité ${product.quantity}`,
        icon: 'success',
        confirmButtonText: 'OK'
      })
    } else {
      Swal.fire({
        title: 'Quantité invalide',
        text: 'La quantité ne peut pas être égale à 0. Veuillez ajuster la quantité.',
        icon: 'warning',
        confirmButtonText: 'Compris'
      });
    }
  }


   updateCartCount() {
    let cart = JSON.parse(localStorage.getItem('cart') || '[]');

    let totalQuantity = cart.length

    let cartCountElement = document.getElementById('cart-count');
    if (cartCountElement) {
      cartCountElement.textContent = totalQuantity.toString();
    }
  }

    showDialog(product: any) {
    if(JSON.stringify(localStorage.getItem("jwtToken")) == 'null') {
      this.display = true;
    } else {
      this.addToCart(product)
    }
    }

    closeDialog() {
      this.display = false;
    }



  toggleRegister() {
    this.isRegistering = !this.isRegistering;
  }


  login() {
    console.log('Connexion avec email:', this.email, 'et mot de passe:', this.password);
    this.display = false;
  }

  register() {
    console.log('Inscription avec:', this.firstName, this.username, this.email, this.password);
    this.display = false;
  }

  openDialog() {
    this.display = true;
  }
  addToFavorites(product: any) {
    console.log(JSON.stringify(localStorage.getItem("accountId")))
    const request: AddFavorisRequest = {
      productId: product.id,
      accountId: Number(localStorage.getItem("accountId"))
    }
    this.favoritsService.addProductToFav(request)
      .subscribe(next => {
        Swal.fire({
          title: 'Ajouté aux favoris !',
          text: `${product.name} a été ajouté à votre liste de favoris.`,
          icon: 'success',
          confirmButtonText: 'Super'
        });
      },
        error => {
          if (error.status === 409) {
            Swal.fire({
              title: 'Déjà dans les favoris',
              text: `${product.name} est déjà présent dans votre liste de favoris.`,
              icon: 'info',
              confirmButtonText: 'OK'
            });
          } else {
            Swal.fire({
              title: 'Erreur',
              text: 'Une erreur est survenue lors de l\'ajout aux favoris.',
              icon: 'error',
              confirmButtonText: 'OK'
            });
          }

        })

  }


  protected readonly JSON = JSON;
  protected readonly localStorage = localStorage;
}
