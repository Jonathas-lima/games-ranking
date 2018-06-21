import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {Jogador} from '../model/jogador.model';
import {JogadorService} from '../service/jogador.service';

@Component({
  selector: 'app-exibir-jogadores',
  templateUrl: './exibir-jogadores.component.html',
  styleUrls: ['./exibir-jogadores.component.css']
})
export class ExibirJogadoresComponent implements  OnChanges{

  @Input() jogadores: Jogador[];
  @Output() delete_event = new EventEmitter();
  @Output() update_event = new EventEmitter();
  @Output() incVitoria_event = new EventEmitter();

  jogadorActive: Jogador;
  indexJogador;
  modal = false;

  constructor(private http: JogadorService) {
  }

  ngOnChanges() {
    this.jogadores.sort(((a, b) => b.numeroVitorias - a.numeroVitorias));
  }

  incrementeVitoria(data: Jogador) {

    data.numeroVitorias++;
    this.incVitoria_event.emit(data);
  }

  incrementePartida(data: Jogador) {
    const index = this.jogadores.indexOf(data);
    data.numeroPartidas++;
    this.http.update(data).subscribe(resp => {
      this.jogadores[index] = resp;
    });
  }

  delete(jogador: Jogador){
    this.delete_event.emit(jogador);
  }

  update(jogador){

     this.jogadorActive.nome = jogador.nome;
     this.jogadorActive.numeroVitorias = jogador.numVitorias;
     this.jogadorActive.numeroPartidas = jogador.numPartidas;
     this.update_event.emit(this.jogadorActive);
     this.modal = false;
  }

  showModal(indx, jogador: Jogador){
    this.indexJogador = indx
    this.jogadorActive = jogador;
    this.modal = true;
  }
  closeModal(event){
    this.modal = event;
  }
}

