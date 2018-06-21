import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Jogador} from '../model/jogador.model';

@Component({
  selector: 'app-modal-jogador',
  templateUrl: './modal-jogador.component.html',
  styleUrls: ['./modal-jogador.component.css']
})
export class ModalJogadorComponent implements OnInit {

  @Input() isVisibleModal;
  @Input() title;
  @Input() tipo_modal;
  @Input() jogador: Jogador;
  @Output () enviarForm = new EventEmitter();
  @Output() closeModal = new EventEmitter();
  constructor() { }

  ngOnInit() {
  }

  handleCancel(){
    this.isVisibleModal = false;
    this.closeModal.emit(this.isVisibleModal);
  }

  save(event){
    this.enviarForm.emit(event);
  }
}
