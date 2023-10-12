import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilesService {

  constructor() { }

  public login(){
    const inputs = document.querySelectorAll(".form__input");
    inputs.forEach(input=>{
        const field = input.parentElement;
        const label = input.previousElementSibling;
        if(field != null && label != null){  
          
          input.addEventListener("focus",()=>{
            field.classList.add("form__item--active")
            label.classList.add("form__label--active")
          });
          
          input.addEventListener("blur",()=>{
            if(input instanceof HTMLInputElement){
              const valor = input.value;
              if(valor === ''){
                  field.classList.remove("form__item--active")
                  label.classList.remove("form__label--active")
              }
            }
          })
        }
    });
  }

  public password(){
    const eyes = document.querySelectorAll(".btn__eye");
    eyes.forEach(eye=>{
        eye.addEventListener("click",()=>{
            const input = eye.previousElementSibling as HTMLInputElement;
            if(input.type=="password"){
                input.type="text"
                eye.setAttribute("src","../../assets/img/eye-close.svg")
            }else{
                input.type="password"
                eye.setAttribute("src","../../assets/img/eye.svg")
            }
        })
    })
  }

  public sidebar(){
    const links = document.querySelectorAll(".sidebar__link--scroll")
    links.forEach(link=>{
        link.addEventListener("click",()=>{
            const arrow = link.querySelector(".sidebar__arrow")
            arrow.classList.toggle("sidebar__arrow--active")

            const menu = link.nextElementSibling as HTMLElement;
            let height = 0;
            if(menu.clientHeight == 0){
                height=menu.scrollHeight;
            }
            menu.style.height = `${height}px`
        })
    })
  }

}
