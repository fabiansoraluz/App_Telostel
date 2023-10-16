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
            const formItem = eye.closest('.form__item'); // Encuentra el elemento .form__item más cercano
            const input = formItem.querySelector('.form__input') as HTMLInputElement;
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

}
