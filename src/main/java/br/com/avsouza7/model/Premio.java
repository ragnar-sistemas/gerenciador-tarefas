package br.com.avsouza7.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSetter;

import br.com.avsouza7.util.FormataMonetario;

public class Premio {

	private Integer faixa;
	@JsonSetter("numeroDeGanhadores")
	private String ganhadores;
	@JsonSetter("valorPremio")
	private BigDecimal vlPremio;
	private String descricaoFaixa;

	public Integer getFaixa() {
		return faixa;
	}

	public void setFaixa(Integer faixa) {
		this.faixa = faixa;
	}

	public String getGanhadores() {
		return ganhadores;
	}

	public void setGanhadores(String ganhadores) {
		this.ganhadores = ganhadores;
	}

	public BigDecimal getVlPremio() {
		return vlPremio;
	}

	public void setVlPremio(BigDecimal vlPremio) {
		this.vlPremio = vlPremio;
	}

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	@Override
	public String toString() {
		return "Premio [faixa=" + faixa + ", ganhadores=" + ganhadores + ", vlPremio=" + FormataMonetario.brasileiro(vlPremio) + ", descricaoFaixa=" + descricaoFaixa + "]";
	}

}
