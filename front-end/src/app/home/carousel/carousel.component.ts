import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ngbd-carousel-basic',
  templateUrl: './carousel.component.html'
})
export default class CarouselComponent implements OnInit {
  images: any;


  constructor() {
  }

  ngOnInit(): void {
   this.images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/1200/600`);
  }
}
