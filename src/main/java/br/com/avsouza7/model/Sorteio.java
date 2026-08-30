package br.com.avsouza7.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.avsouza7.enuns.LoteriaEnum;
import br.com.avsouza7.json.CustomDateDeserializer;
import br.com.avsouza7.json.CustomDateSerializer;
import br.com.avsouza7.util.DateUtil;
import br.com.avsouza7.util.FormataMonetario;

public class Sorteio {
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonSetter("dataApuracao")
    private Date dtSorteio;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonSetter("dataProximoConcurso")
    private Date dtProximoSorteio;
    private Long idLoteria;
    private Long idConcurso;
    @JsonSetter("listaDezenas")
    private List<Dezena> dezenas = new ArrayList<>();

    @JsonSetter("listaRateioPremio")
    private List<Premio> premios = new ArrayList<>();

    @JsonSetter("valorEstimadoProximoConcurso")
    private BigDecimal valorAcumulado;
    @JsonSetter("valorAcumuladoConcursoEspecial")
    private BigDecimal valorAcumuladoConcursoEspecial;

    public Date getDtSorteio() {
	return dtSorteio;
    }

    public void setDtSorteio(Date dtSorteio) {
	this.dtSorteio = dtSorteio;
    }

    public Long getIdLoteria() {
	return idLoteria;
    }

    public void setIdLoteria(Long idLoteria) {
	this.idLoteria = idLoteria;
    }

    public List<Dezena> getDezenas() {
	return dezenas;
    }

    public void setDezenas(List<Dezena> dezenas) {
	this.dezenas = dezenas;
    }

    public Long getIdConcurso() {
	return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
	this.idConcurso = idConcurso;
    }

    public List<Premio> getPremios() {
	return premios;
    }

    public void setPremios(List<Premio> premios) {
	this.premios = premios;
    }

    public Date getDtProximoSorteio() {
	return dtProximoSorteio;
    }

    public void setDtProximoSorteio(Date dtProximoSorteio) {
	this.dtProximoSorteio = dtProximoSorteio;
    }

    public BigDecimal getValorAcumulado() {
	return valorAcumulado;
    }

    public void setValorAcumulado(BigDecimal valorAcumulado) {
	this.valorAcumulado = valorAcumulado;
    }

    public String getValorAcumuladoFormatado() {
	return FormataMonetario.brasileiro(valorAcumulado);
    }

    public String getDtProximoSorteioFormatado() {
	return DateUtil.convertBr(dtProximoSorteio);
    }

    public String getValorAcumuladoConcursoEspecialFormatado() {
	return FormataMonetario.brasileiro(valorAcumuladoConcursoEspecial);
    }

    public BigDecimal getValorAcumuladoConcursoEspecial() {
	return valorAcumuladoConcursoEspecial;
    }

    public void setValorAcumuladoConcursoEspecial(BigDecimal valorAcumuladoConcursoEspecial) {
	this.valorAcumuladoConcursoEspecial = valorAcumuladoConcursoEspecial;
    }

    public String getAcumuladoSorteioEspecial() {
	return "Acumulado para o sorteio especial de " + LoteriaEnum.getById(idLoteria).getSorteioEspecial() + ":";
    }

    public String getDtSorteioFormatado() {
	return DateUtil.convertBr(dtSorteio);
    }

    public LoteriaEnum getLoteriaEnum() {
	return LoteriaEnum.getById(idLoteria);
    }

}
