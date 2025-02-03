import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Footer} from "primeng/api";
import {NgIf} from "@angular/common";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ButtonDirective} from "primeng/button";
import {ChipsModule} from "primeng/chips";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  standalone: true,
  imports: [
    FormsModule,
    Footer,
    NgIf,
    InputTextareaModule,
    ButtonDirective,
    ChipsModule
  ],
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  email: string = '';
  message: string = '';
  isMessageSent: boolean = false;

  constructor() {}



  onSubmit() {
    if (this.email && this.message && this.message.length <= 300) {
      console.log('Email:', this.email);
      console.log('Message:', this.message);

      this.isMessageSent = true;

      Swal.fire({
        title: 'Succès!',
        text: 'Demande de contact envoyée avec succès!!.',
        icon: 'success',
        confirmButtonText: 'OK',
        timer: 3000,
      });

      this.email = '';
      this.message = '';
    } else {
      if (!this.email) {
        Swal.fire({
          title: 'Erreur!',
          text: 'Veuillez entrer votre email.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
      } else if (!this.message) {
        Swal.fire({
          title: 'Erreur!',
          text: 'Veuillez entrer un message.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
      } else if (this.message.length > 300) {
        Swal.fire({
          title: 'Erreur!',
          text: 'Le message ne doit pas dépasser 300 caractères.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
      }
    }
  }


}
