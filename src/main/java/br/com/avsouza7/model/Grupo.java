package br.com.avsouza7.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grupo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idGrupo")
  private Long idGrupo;
  private String nome;

  public Long getIdGrupo() {
    return idGrupo;
  }

  public void setIdGrupo(Long idGrupo) {
    this.idGrupo = idGrupo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  private String observacao;



}
