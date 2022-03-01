import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadJsService {

  constructor() { }

  loadScript(src:string) {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    document.getElementsByTagName('body')[0].appendChild(script);
    script.src = src;
  }
}
