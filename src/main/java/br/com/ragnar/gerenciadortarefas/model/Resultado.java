package br.com.ragnar.gerenciadortarefas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Resultado {

	private Long idConcurso;
	private Integer nuAcertos;
	private String vlPremio;
	private Date dtSorteio;
	private List<Dezena> dezenas;

	public Resultado() {
		nuAcertos = 0;
	}

	public Long getIdConcurso() {
		return idConcurso;
	}

	public void setIdConcurso(Long idConcurso) {
		this.idConcurso = idConcurso;
	}

	public String getVlPremio() {
		return vlPremio;
	}

	public void setVlPremio(String vlPremio) {
		this.vlPremio = vlPremio;
	}

	public List<Dezena> getDezenas() {
		if (dezenas == null) {
			dezenas = new ArrayList<>();
		}
		return dezenas;
	}

	public void setDezenas(List<Dezena> dezenas) {
		this.dezenas = dezenas;
	}

	public Date getDtSorteio() {
		return dtSorteio;
	}

	public void setDtSorteio(Date dtSorteio) {
		this.dtSorteio = dtSorteio;
	}

	public Integer getNuAcertos() {
		return nuAcertos;
	}

	public void setNuAcertos(Integer nuAcertos) {
		this.nuAcertos = nuAcertos;
	}

}
