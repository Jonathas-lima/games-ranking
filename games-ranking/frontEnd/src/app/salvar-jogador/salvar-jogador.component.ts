import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Jogador} from '../model/jogador.model';

@Component({
  selector: 'app-salvar-jogador',
  templateUrl: './salvar-jogador.component.html',
  styleUrls: ['./salvar-jogador.component.css']
})
export class SalvarJogadorComponent implements OnChanges {

  constructor(private formBuilder: FormBuilder) { }

  salvarForm: FormGroup;
  @Input() tipo_modal;
  @Input() jogador: Jogador;
  @Output() nomeJogador = new EventEmitter()

  ngOnChanges() {

    this.salvarForm = this.formBuilder.group({
      nome: new FormControl((this.jogador) ? this.jogador.nome : '', [Validators.required]),
      numPartidas: new FormControl((this.jogador) ? this.jogador.numeroPartidas : ''),
      numVitorias: new FormControl((this.jogador) ? this.jogador.numeroVitorias : '')
    });
  }

  submitForm(){
    this.nomeJogador.emit(this.salvarForm.value);
    if(this.tipo_modal === 'S'){
      this.salvarForm.reset();
    }
  }

}
