package br.com.avsouza7.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import br.com.avsouza7.enuns.GrupoEnum;
import br.com.avsouza7.enuns.LoteriaEnum;

@Entity
@Table(name = "apostas")
public class Aposta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAposta")
  private Long idAposta;
  @Column(name = "nuConcurso")
  private Long nuConcurso;
  @NotNull(message = "Loteria é obrigatório")
  @Column(name = "idLoteria", length = 50, nullable = false)
  private Long idLoteria;
  @Column(name = "dtSorteio")
  private Date dtSorteio;
  private String dezenasApostadas;
  private Long idGrupo;
  @Transient
  private List<Dezena> dezenas = new ArrayList<>();

  public Long getIdAposta() {
    return idAposta;
  }

  public void setIdAposta(Long idAposta) {
    this.idAposta = idAposta;
  }

  public Long getNuConcurso() {
    return nuConcurso;
  }

  public void setNuConcurso(Long nuConcurso) {
    this.nuConcurso = nuConcurso;
  }

  public Long getIdLoteria() {
    return idLoteria;
  }

  public void setIdLoteria(Long idLoteria) {
    this.idLoteria = idLoteria;
  }

  public Date getDtSorteio() {
    return dtSorteio;
  }

  public void setDtSorteio(Date dtSorteio) {
    this.dtSorteio = dtSorteio;
  }

  public List<Dezena> getDezenas() {
    return dezenas;
  }

  public void setDezenas(List<Dezena> dezenas) {
    this.dezenas = dezenas;
  }

  public String getDezenasApostadas() {
    return dezenasApostadas;
  }

  public void setDezenasApostadas(String dezenasApostadas) {
    this.dezenasApostadas = dezenasApostadas;
  }

  public LoteriaEnum getLoteria() {
    return LoteriaEnum.getById(idLoteria);
  }

  public Long getIdGrupo() {
    return idGrupo;
  }

  public void setIdGrupo(Long idGrupo) {
    this.idGrupo = idGrupo;
  }

  public GrupoEnum getGrupo() {
    return GrupoEnum.getById(idGrupo);
  }
}
