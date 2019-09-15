package br.com.ragnar.gerenciadortarefas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aposta {

	private Long idAposta;
	private Long idLoteria;
	private Date dtSorteio;
	private List<Dezena> dezenas;

	public Long getIdAposta() {
		return idAposta;
	}

	public void setIdAposta(Long idAposta) {
		this.idAposta = idAposta;
	}

	public Date getDtSorteio() {
		return dtSorteio;
	}

	public void setDtSorteio(Date dtSorteio) {
		this.dtSorteio = dtSorteio;
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

	public Long getIdLoteria() {
		return idLoteria;
	}

	public void setIdLoteria(Long idLoteria) {
		this.idLoteria = idLoteria;
	}

}
