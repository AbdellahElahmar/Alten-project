import {
  Component, OnInit,
} from "@angular/core";
import {Router, RouterModule} from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import {LoginComponent} from "./login/login.component";
import {SidebarModule} from "primeng/sidebar";
import {CurrencyPipe, NgForOf, NgIf} from "@angular/common";
import {ButtonDirective} from "primeng/button";
import {PanierComponent} from "./panier/panier.component";
import {SplitButtonModule} from "primeng/splitbutton";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, LoginComponent, SidebarModule, CurrencyPipe, NgForOf, ButtonDirective, PanierComponent, NgIf, SplitButtonModule],
})
export class AppComponent implements OnInit{
  title = "ALTEN SHOP";
  display: boolean = false;
  showSidebar: boolean = false;
  menuItems: any[] = []
  totalProducts: number = JSON.parse(localStorage.getItem('cart') || '[]').length
  protected readonly localStorage = localStorage;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.menuItems = [
      {
        label: 'Se déconnecter',
        icon: 'pi pi-sign-out',
        command: () => this.logout()
      }
    ];
  }


  showDialog() {
    this.display = true;
  }
  closeDialog() {
    this.display = false;
  }


  closeSideBar() {
    this.showSidebar = false
  }

  logout() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('email');
    localStorage.removeItem('accountId');
    localStorage.removeItem('cart');
    this.router.navigate(['/home']);
  }

}
