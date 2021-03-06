import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'bs-book-form',
  templateUrl: './book-form.component.html',
  styles: []
})
export class BookFormComponent implements OnInit {

  book = {
    title: "dummy title",
    description: "dummy description",
    unitCost: "123",
    isbn: "123-3456-567",
    nbOfPages: "456",
    Language: "English",
    imageURL: "http://ecx.images-amazon.com/images/I/51amFVZbyKL._SL160_.jpg"
  }

  constructor(private router: Router) { }

  ngOnInit() {
  }

  create() {
    // call REST API
    this.router.navigate(['/book-list']);
  }
}
