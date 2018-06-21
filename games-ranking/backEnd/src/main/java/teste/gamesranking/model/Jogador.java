package teste.gamesranking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import teste.gamesranking.exceptions.InconsistentDataException;
import teste.gamesranking.exceptions.NameInvalidException;
import teste.gamesranking.exceptions.NegativeNumberException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jonathas
 *
 * Classe modelo de Jogador
 * */

@Entity
@Table(name = "jogadores")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Jogador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private int numeroPartidas;

    private int numeroVitorias;

    //manter a data de criacao
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    //manter a data da ultima atualizacao
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    //para validar os dados vindo do frontEnd
    public void validate(){

        if(this.numeroPartidas < 0 ){
            throw new NegativeNumberException("partidas");
        }else if( this.numeroVitorias < 0){
            throw new NegativeNumberException("vitorias");
        }else if (this.numeroVitorias > this.numeroPartidas){
            throw new InconsistentDataException("Vitórias","partidas");
        }else if(!this.nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]+$")){
            throw new NameInvalidException(this.nome);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroPartidas() {
        return numeroPartidas;
    }

    public void setNumeroPartidas(int numeroPartidas) {
        this.numeroPartidas = numeroPartidas;
    }

    public int getNumeroVitorias() {
        return numeroVitorias;
    }

    public void setNumeroVitorias(int numeroVitorias) {
        this.numeroVitorias = numeroVitorias;
    }
}
