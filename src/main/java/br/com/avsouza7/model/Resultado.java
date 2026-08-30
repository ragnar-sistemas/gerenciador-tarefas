package br.com.avsouza7.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.avsouza7.enuns.LoteriaEnum;

public class Resultado {

	private Long idConcurso;
	private Integer nuAcertos;
	private String vlPremio;
	private Date dtSorteio;
	private Long idLoteria;
	private List<Dezena> dezenas = new ArrayList<>();

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

	public Long getIdLoteria() {
		return idLoteria;
	}

	public void setIdLoteria(Long idLoteria) {
		this.idLoteria = idLoteria;
	}

	public Integer getFaixa() {
		Optional<Integer> faixa = Optional.ofNullable(LoteriaEnum.getById(idLoteria).faixas().get(nuAcertos));
		if (faixa.isEmpty()) {
			faixa = Optional.of(0);
		}
		return faixa.get();
	}

	@Override
	public String toString() {
		return "Resultado [idConcurso=" + idConcurso + ", nuAcertos=" + nuAcertos + ", vlPremio=" + vlPremio + ", dtSorteio=" + dtSorteio + ", idLoteria=" + idLoteria + ", dezenas=" + dezenas + "]";
	}

}
