package br.com.avsouza7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPessoa;

  private String nome;

  private String observacao;
  
  private String chavePix;

  private String flAtivo;

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public Long getIdPessoa() {
    return idPessoa;
  }

  public void setIdPessoa(Long idPessoa) {
    this.idPessoa = idPessoa;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getChavePix() {
    return chavePix;
  }

  public void setChavePix(String chavePix) {
    this.chavePix = chavePix;
  }

  public String getFlAtivo() {
    return flAtivo;
  }

  public void setFlAtivo(String flAtivo) {
    this.flAtivo = flAtivo;
  }

  public String getAtivo() {
    return Objects.isNull(flAtivo) ||"S".equals(flAtivo) ? "Ativo" : "Inativo";
  }
}

