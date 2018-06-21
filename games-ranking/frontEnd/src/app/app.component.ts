import {Component, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {Jogador} from './model/jogador.model';
import {JogadorService} from './service/jogador.service';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ExibirJogadoresComponent} from './exibir-jogadores/exibir-jogadores.component';
import {NzMessageService} from 'ng-zorro-antd';
import {SalvarJogadorComponent} from './salvar-jogador/salvar-jogador.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{


  jogadores = new Array();
  isOK = false;
  isVisibleModal = false;

  constructor(private jogadorService: JogadorService,
              private message: NzMessageService){}

  ngOnInit(){
    this.jogadorService.jogadores()
      .subscribe( jogadores => {
        this.jogadores = jogadores;
        this.isOK = true;
      });
  }


  showModal(){
    this.isVisibleModal = true;
  }

  save(event: string){

    this.jogadorService.salvar(event).subscribe( resposta =>{

      this.jogadorService.jogadores().subscribe(resp => {
        this.jogadores = resp;
      });
      this.message.create('success', `${resposta.nome} salvo com sucesso!`);
      this.isVisibleModal = false;
    });
  }

  update(jogador: Jogador, msg: string){

    this.jogadorService.update(jogador).subscribe(resp =>{
      this.jogadores =  this.jogadores.filter( x => x.id !== jogador.id);
      this.jogadores.push(resp);
      if(msg === 'atualizar'){
        this.message.create('success', `${jogador.nome} atualizado com sucesso!`);
      }else{
        this.message.create('success', `Vitória adicionada!`);
      }
    });
  }

  delete(jogador: Jogador){

    this.jogadorService.delete(jogador).subscribe( resp =>{
      this.jogadores =  this.jogadores.filter( x => x.id !== jogador.id);
      this.message.create('success', `${jogador.nome} excluído com sucesso!`);
    });
  }

  closeModal(event){
    this.isVisibleModal = event;
  }

}
