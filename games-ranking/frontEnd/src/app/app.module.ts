import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {JogadorService} from './service/jogador.service';
import {HttpClientModule} from '@angular/common/http';
import { NZ_I18N, en_US } from 'ng-zorro-antd';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { SalvarJogadorComponent } from './salvar-jogador/salvar-jogador.component';
import { ExibirJogadoresComponent } from './exibir-jogadores/exibir-jogadores.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ModalJogadorComponent } from './modal-jogador/modal-jogador.component';
registerLocaleData(en);


@NgModule({
  declarations: [
    AppComponent,
    SalvarJogadorComponent,
    ExibirJogadoresComponent,
    ModalJogadorComponent
  ],

  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgZorroAntdModule.forRoot()
  ],

  providers: [
    JogadorService,
    { provide: NZ_I18N, useValue: en_US }
    ],

  bootstrap: [AppComponent]
})
export class AppModule { }
