import { Component, ElementRef, OnInit } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit{

  constructor(private elementRef: ElementRef){}

  ngOnInit(): void {
    const links = this.elementRef.nativeElement.querySelectorAll('.scroll-link');
    for (const link of links) {
      link.addEventListener('click', this.smoothScroll.bind(this));
    }
  }
  smoothScroll(e) {
    e.preventDefault();
    const targetId = e.target.getAttribute('href');
    const targetPosition = document.querySelector(targetId).offsetTop;
    const startPosition = window.pageYOffset;
    const distance = targetPosition - startPosition;
    const duration = 1000; // Duración del scroll en milisegundos
    let start = null;

    window.requestAnimationFrame(step);

    function step(timestamp) {
    if (!start) start = timestamp;
    const progress = timestamp - start;
    window.scrollTo(0, easeInOutCubic(progress, startPosition, distance, duration));
    if (progress < duration) window.requestAnimationFrame(step);
    }

    // Función para el efecto suave de scroll (easeInOutCubic)
    function easeInOutCubic(t, b, c, d) {
    t /= d / 2;
    if (t < 1) return c / 2 * t * t * t + b;
    t -= 2;
    return c / 2 * (t * t * t + 2) + b;
    }
  }

}
