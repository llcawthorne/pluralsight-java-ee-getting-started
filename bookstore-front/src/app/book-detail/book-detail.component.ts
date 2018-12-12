import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'bs-book-detail',
  templateUrl: './book-detail.component.html',
  styles: []
})
export class BookDetailComponent implements OnInit {

  book = {
    title: "dummy title",
    description: "dummy description",
    unitCost: "123",
    isbn: "123-3456-567",
    nbOfPages: "456",
    Language: "English"
  }

  constructor() { }

  ngOnInit() {
  }

}
