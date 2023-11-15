import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'productoFiltros'
})
export class ProductoFiltrosPipe implements PipeTransform {

  transform(value: any, ...arg: any[]): any {
    const resultado = []

    for(const prod of value){
      if(prod.nombre.toLowerCase().indexOf(arg) > -1){
        resultado.push(prod)
      }
    }
    return resultado
  }

}
