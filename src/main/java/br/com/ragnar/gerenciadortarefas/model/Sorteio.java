package br.com.ragnar.gerenciadortarefas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sorteio {

	private Date dtSorteio;
	private Long idLoteria;
	private Long idConcurso;
	private List<Dezena> dezenas;
	public List<Premio> premios;

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
		if (dezenas == null) {
			dezenas = new ArrayList<>();
		}
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
		if (premios == null) {
			premios = new ArrayList<>();
		}
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}

}
