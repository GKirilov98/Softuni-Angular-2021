import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  sessionStorage;
  constructor(
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.sessionStorage = sessionStorage
  }

  logOut() {
    sessionStorage.clear()
    this.router.navigate(['/'])
  }
}
