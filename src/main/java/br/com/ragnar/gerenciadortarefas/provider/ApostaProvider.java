package br.com.ragnar.gerenciadortarefas.provider;

import java.util.ArrayList;
import java.util.List;

import br.com.ragnar.gerenciadortarefas.enuns.LoteriasEnum;
import br.com.ragnar.gerenciadortarefas.filter.ResultadoFilter;
import br.com.ragnar.gerenciadortarefas.model.Aposta;
import br.com.ragnar.gerenciadortarefas.model.Dezena;

public class ApostaProvider {

	private static final Long ID_APOSTA = 1l;

	private int lotofacil1[] = new int[] { 1, 2, 4, 5, 6, 8, 12, 14, 15, 17, 18, 19, 20, 21, 23 };
	private int lotofacil2[] = new int[] { 3, 4, 5, 6, 8, 11, 12, 13, 14, 15, 16, 17, 18, 23, 24 };
	private int lotofacil3[] = new int[] { 1, 3, 5, 6, 7, 8, 14, 16, 17, 18, 19, 20, 21, 22, 23 };

	private int lotofacil4[] = new int[] { 2, 3, 5, 6, 7, 9, 10, 11, 2, 13, 15, 19, 22, 23, 24, 25 };
	private int lotofacil5[] = new int[] { 1, 2, 3, 4, 5, 7, 10, 11, 12, 13, 14, 17, 19, 22, 24, 25 };
	private int lotofacil6[] = new int[] { 2, 3, 5, 7, 9, 10, 11, 12, 15, 18, 19, 20, 21, 23, 24, 25 };

	private int megasena[] = new int[] { 13, 16, 23, 39, 42, 58 };

	private int quina[] = new int[] { 28, 37, 42, 58, 66 };

	public List<Aposta> getApostas(ResultadoFilter filter) {
		List<Aposta> apostas = new ArrayList<>();
		if (LoteriasEnum.LOTOFACIL.equals(LoteriasEnum.getById(filter.getIdLoteria()))) {
			apostas.add(umaAposta(lotofacil1));
		} else if (LoteriasEnum.MEGASENA.equals(LoteriasEnum.getById(filter.getIdLoteria()))) {
			apostas.add(umaAposta(megasena));
		} else if (LoteriasEnum.QUINA.equals(LoteriasEnum.getById(filter.getIdLoteria()))) {
			apostas.add(umaAposta(quina));
		}
		return apostas;
	}

	private Aposta umaAposta(int valores[]) {
		Aposta aposta = new Aposta();
		aposta.setIdAposta(ID_APOSTA);
		for (int i = 0; i < valores.length; i++) {
			aposta.getDezenas().add(umaDezena(Long.valueOf(valores[i])));
		}
		return aposta;
	}

	private Dezena umaDezena(Long idDezena) {
		Dezena dezena = new Dezena();
		dezena.setIdDezena(idDezena);
		return dezena;
	}

}
