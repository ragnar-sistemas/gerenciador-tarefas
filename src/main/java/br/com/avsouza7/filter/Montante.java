package br.com.avsouza7.filter;

import java.math.BigDecimal;

public class Montante {

	private BigDecimal montante;
	private Long idLoteria;

	public Long getIdLoteria() {
		return idLoteria;
	}

	public void setIdLoteria(Long idLoteria) {
		this.idLoteria = idLoteria;
	}

	public BigDecimal getMontante() {
		return montante;
	}

	public void setMontante(BigDecimal montante) {
		this.montante = montante;
	}

}
