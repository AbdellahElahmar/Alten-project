import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {CurrencyPipe, NgForOf, NgIf} from "@angular/common";
import {Footer} from "primeng/api";
import {SidebarModule} from "primeng/sidebar";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-panier',
  standalone: true,
  imports: [
    ButtonDirective,
    CurrencyPipe,
    Footer,
    NgForOf,
    SidebarModule,
    NgIf,
    FormsModule
  ],
  templateUrl: './panier.component.html',
  styleUrl: './panier.component.css'
})
export class PanierComponent implements OnInit{
  @Input() showSidebar: boolean = false
  @Output() closeEvent = new EventEmitter<any>()
  cartItems: any[] = []

  ngOnInit() {
    this.cartItems = JSON.parse(localStorage.getItem('cart') || '[]');
  }

  onSidebarClose() {
    this.closeEvent.emit()
  }

  get totalPrice() {
    return this.cartItems.reduce((total, item) => total + item.quantity * item.price, 0);
  }

  updateQuantity(index: number) {
    const newQuantity = this.cartItems[index].quantity;

    if (newQuantity < 0) {
      this.cartItems[index].quantity = 0;
    }
  }

}
