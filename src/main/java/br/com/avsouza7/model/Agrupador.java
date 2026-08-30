package br.com.avsouza7.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agrupador {
	private Long idConcurso;
	private Date dtSorteio;
	private Map<Long, List<Aposta>> mapa = new HashMap<>();

	public void addApostas(long idConcurso, Date dtSorteio, List<Aposta> apostas) {
		this.dtSorteio = dtSorteio;
		mapa.put(idConcurso, apostas);
	}

	public Long getIdConcurso() {
		return idConcurso;
	}

	public Map<Long, List<Aposta>> getApostas() {
		return mapa;
	}

	public Date getDtSorteio() {
		return dtSorteio;
	}
}
