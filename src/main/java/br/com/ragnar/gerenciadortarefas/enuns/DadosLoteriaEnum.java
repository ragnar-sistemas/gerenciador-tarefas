package br.com.ragnar.gerenciadortarefas.enuns;

import static java.util.Arrays.asList;

import java.util.List;

public enum DadosLoteriaEnum {

	MEGASENA("megasena", 11, 16, asList(6, 5, 4)), 
	LOTOFACIL("lotofacil", 10, 24, asList(15, 14, 13, 12, 11)), 
	QUINA("quina", 10, 14, asList(5, 4, 3, 2)), 
	LOTOMANIA("lotomania", 0, 0, asList(20, 19, 18, 17, 16, 15, 0)), 
	TIMEMANIA("timemania", 0, 0, asList(7, 6, 5, 4, 3)), 
	DUPLA_SENA("duplasena", 0, 0, asList(6, 5, 4, 3)), 
	DIA_DE_SORTE("diadesorte", 0, 0, asList(7, 6, 5, 4));

	private final String url;
	private final int inicio;
	private final int fim;
	private final List<Integer> faixa;

	private DadosLoteriaEnum(String url, int inicio, int fim, List<Integer> faixa) {
		this.url = url;
		this.inicio = inicio;
		this.fim = fim;
		this.faixa = faixa;
	}

	public String getUrl() {
		return url;
	}

	public int getInicio() {
		return inicio;
	}

	public int getFim() {
		return fim;
	}

	public List<Integer> getFaixa() {
		return faixa;
	}
}
