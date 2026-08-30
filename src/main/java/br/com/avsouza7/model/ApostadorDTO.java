package br.com.avsouza7.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import br.com.avsouza7.util.FormataMonetario;

public class ApostadorDTO {

  private Long idPessoa;
  private String nome;
  @NotNull
  @DecimalMin("0.01")
  private BigDecimal aporte;
  private String chavePix;
  private String ativo;

  public ApostadorDTO() {
    aporte = BigDecimal.ZERO;
  }

  public ApostadorDTO(Pessoa pessoa) {
    this();
    setIdPessoa(pessoa.getIdPessoa());
    setNome(pessoa.getNome());
    setChavePix(pessoa.getChavePix());
    setAtivo(Objects.isNull(pessoa.getAtivo()) ? "S" : pessoa.getAtivo());
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

  public BigDecimal getAporte() {
    return aporte;
  }

  public void setAporte(BigDecimal aporte) {
    this.aporte = aporte;
  }

  public String getValorPremio() {
    return FormataMonetario.brasileiro(aporte);
  }

  public String getChavePix() {
    return chavePix;
  }

  public void setChavePix(String chavePix) {
    this.chavePix = chavePix;
  }

  public String getAtivo() {
    return ativo;
  }

  public void setAtivo(String ativo) {
    this.ativo = ativo;
  }
}
