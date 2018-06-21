import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Jogador} from '../model/jogador.model';
import {environment} from '../../environments/environment';


@Injectable()
export class JogadorService{

  private httpOptions = {
       headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient){}

  jogadores(): Observable<Jogador[]>{

    return this.http.get<Jogador[]>(`${environment.api}/jogadores`);
  }

  salvar(obj: any): Observable<Jogador>{

    const body = `{
            	"nome" : "${obj.nome}",
	            "numeroPartidas" : ${(obj.numPartidas) ? obj.numPartidas : '0'},
	            "numeroVitorias" : ${(obj.numVitorias) ? obj.numVitorias : '0'}
            }`;

    return this.http.post<Jogador>(`${environment.api}/jogador`, body, this.httpOptions);
  }

  update(jogador: Jogador){

    const body = `{
            	"nome" : "${jogador.nome}",
	            "numeroPartidas" : ${jogador.numeroPartidas},
	            "numeroVitorias" : ${jogador.numeroVitorias}
            }`;

    return this.http.put<Jogador>(`${environment.api}/jogador/${jogador.id}`, body, this.httpOptions);
  }

  delete(jogador: Jogador){
    return this.http.delete(`${environment.api}/jogador/${jogador.id}`);
  }
}
