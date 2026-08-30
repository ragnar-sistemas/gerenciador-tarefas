package br.com.avsouza7.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.avsouza7.util.FormataMonetario;

@Entity
public class Apostador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApostador;
    private Long idLoteria;
    private Long idConcurso;
    private Long idGrupo;
    private BigDecimal aporte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    public Long getIdApostador() {
	return idApostador;
    }

    public void setIdApostador(Long idApostador) {
	this.idApostador = idApostador;
    }

    public Long getIdLoteria() {
	return idLoteria;
    }

    public void setIdLoteria(Long idLoteria) {
	this.idLoteria = idLoteria;
    }

    public Long getIdConcurso() {
	return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
	this.idConcurso = idConcurso;
    }

    public Long getIdGrupo() {
	return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
	this.idGrupo = idGrupo;
    }

    public BigDecimal getAporte() {
	return aporte;
    }

    public void setAporte(BigDecimal aporte) {
	this.aporte = aporte;
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

    public String getValorDoAporte() {
	return FormataMonetario.brasileiro(aporte);
    }
}
