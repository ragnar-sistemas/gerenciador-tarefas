package br.com.avsouza7.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.avsouza7.enuns.GrupoEnum;
import br.com.avsouza7.enuns.LoteriaEnum;

public class CadastroAposta {

  private Long idAposta;
  @NotNull
  private Long idConcurso;
  private Long idLoteria;
  private Long idGrupo;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dtSorteio;
  private List<String> dezenas;
  private List<ApostadorDTO> apostadores;

  public CadastroAposta() {
    dezenas = new ArrayList<>();
    apostadores = new ArrayList<>();
  }


  public Long getIdConcurso() {
    return idConcurso;
  }

  public void setIdConcurso(Long idConcurso) {
    this.idConcurso = idConcurso;
  }

  public Long getIdLoteria() {
    return idLoteria;
  }

  public void setIdLoteria(Long idLoteria) {
    this.idLoteria = idLoteria;
  }

  public Long getIdGrupo() {
    return idGrupo;
  }

  public void setIdGrupo(Long idGrupo) {
    this.idGrupo = idGrupo;
  }

  public List<String> getDezenas() {
    return dezenas;
  }

  public void setDezenas(List<String> dezenas) {
    this.dezenas = dezenas;
  }

  public LoteriaEnum getLoteriaEnum() {
    return LoteriaEnum.getById(idLoteria);
  }

  public GrupoEnum getGrupoEnum() {
    return GrupoEnum.getById(idGrupo);
  }

  public Long getIdAposta() {
    return idAposta;
  }

  public void setIdAposta(Long idAposta) {
    this.idAposta = idAposta;
  }

  public List<ApostadorDTO> getApostadores() {
    return apostadores;
  }

  public void setApostadores(List<ApostadorDTO> apostadores) {
    this.apostadores = apostadores;
  }

  public Date getDtSorteio() {
    return dtSorteio;
  }

  public void setDtSorteio(Date dtSorteio) {
    this.dtSorteio = dtSorteio;
  }

}
