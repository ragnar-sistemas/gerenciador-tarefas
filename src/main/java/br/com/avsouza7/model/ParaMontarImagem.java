package br.com.avsouza7.model;

import java.util.List;

import br.com.avsouza7.enuns.LoteriaEnum;

public class ParaMontarImagem {

	private List<Resultado> resultados;
	private Sorteio sorteio;
	private String valorDoPremio;
	private LoteriaEnum loteriaEnum;
	private Long idLoteria;

	public List<Resultado> getResultados() {
		return resultados;
	}

	public ParaMontarImagem setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
		return this;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public ParaMontarImagem setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
		return this;
	}

	public String getValorDoPremio() {
		return valorDoPremio;
	}

	public ParaMontarImagem setValorDoPremio(String valorDoPremio) {
		this.valorDoPremio = valorDoPremio;
		return this;
	}

	public LoteriaEnum getLoteriaEnum() {
		return loteriaEnum;
	}

	public ParaMontarImagem setLoteriaEnum(LoteriaEnum loteriaEnum) {
		this.loteriaEnum = loteriaEnum;
		return this;
	}

	public Long getIdLoteria() {
		return idLoteria;
	}

	public ParaMontarImagem setIdLoteria(Long idLoteria) {
		this.idLoteria = idLoteria;
		return this;
	}

}
