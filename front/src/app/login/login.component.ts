
import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {DialogModule} from "primeng/dialog";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ButtonDirective} from "primeng/button";
import {ChipsModule} from "primeng/chips";
import {Router} from "@angular/router";
import {AuthService} from "./data-access/auth.service";
import Swal from "sweetalert2";
import {LoginRequest, RegisterRequest} from "./data-access/Auth.model";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    DialogModule,
    FormsModule,
    NgIf,
    ButtonDirective,
    ChipsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent  implements OnInit {
  private errorMessage: string='';
  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {
    this.authForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      firstName: [''],
      username: ['']
    });
  }

  public readonly appTitle = "ALTEN SHOP";
  @Input() display: boolean = false;
  @Output() closeEvent= new EventEmitter<any>();
  firstName: string = '';
  username: string = '';
  public searchQuery: string = "";
  isRegistering: boolean = false;
  isError: boolean = false


  authForm: FormGroup;



  protected email: string= "" ;
  protected password: string= "";

  ngOnInit() {
    localStorage.setItem('cart', JSON.stringify([]))

  }



  toggleRegister() {
    this.isRegistering = true
    this.authForm.get('firstName')?.setValidators(Validators.required)
    this.authForm.get('username')?.setValidators(Validators.required)
  }
  login() {

    if (this.authForm.valid) {
      const request: LoginRequest = {
        ...this.authForm.value
      }
      this.authService.login(request).subscribe(
        (response) => {
          this.authService.saveToken(response.id, response.email, response.token);

          Swal.fire({
            title: 'Connexion réussie!',
            text: 'Bienvenue sur votre tableau de bord.',
            icon: 'success',
            confirmButtonText: 'Continuer'
          })
        },
        (error) => {
          this.isError = true
          this.errorMessage = "Email ou mot de passe incorrect";
          console.error('Erreur de connexion:', error);

          Swal.fire({
            title: 'Erreur!',
            text: 'Email ou mot de passe incorrect.',
            icon: 'error',
            confirmButtonText: 'Réessayer'
          });
        },
        () => {
          this.isError = false
          this.close()
        }
      );
    } else {
      this.isError = true;
    }

  }

  register() {
    if (this.authForm.valid) {
      const request: RegisterRequest = {
        ...this.authForm.value
      }
      this.authService.createAccount(request)
        .subscribe(next => {
            Swal.fire({
              title: 'Succés!',
              text: 'Compte cré avec succés.',
              icon: 'success',
              confirmButtonText: 'Continuer'
            })
          },
          () => {
            Swal.fire({
              title: 'Erreur!',
              text: 'Assurez vous de la validité des champs !',
              icon: 'error',
              confirmButtonText: 'Réessayer'
            });
            },
          () => {
            this.display = false;
          })
    } else {
      this.isError = true;
    }
  }

  openDialog() {
    this.display = true;
  }
  close() {
    console.log("close")
    this.closeEvent.emit()
  }

}
