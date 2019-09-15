package br.com.ragnar.gerenciadortarefas.filter;

import br.com.ragnar.gerenciadortarefas.enuns.DadosLoteriaEnum;
import br.com.ragnar.gerenciadortarefas.enuns.LoteriasEnum;

public class ResultadoFilter {

	private Long idConcurso;
	private Long idLoteria;

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

	public LoteriasEnum getLoteriasEnum() {
		return LoteriasEnum.getById(idLoteria);
	}

	public DadosLoteriaEnum getDados() {
		return getLoteriasEnum().getDadosLoteriaEnum();
	}
}
