package br.com.ragnar.gerenciadortarefas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ragnar.gerenciadortarefas.filter.ResultadoFilter;
import br.com.ragnar.gerenciadortarefas.model.Aposta;
import br.com.ragnar.gerenciadortarefas.model.Dezena;
import br.com.ragnar.gerenciadortarefas.model.Premio;
import br.com.ragnar.gerenciadortarefas.model.Resultado;
import br.com.ragnar.gerenciadortarefas.model.Sorteio;
import br.com.ragnar.gerenciadortarefas.provider.ApostaProvider;
import br.com.ragnar.gerenciadortarefas.provider.SorteioProvider;

@Service
public class ResultadoService {

	private ApostaProvider apostaProvider;
	private SorteioProvider sorteioProvider;

	public ResultadoService() {
		apostaProvider = new ApostaProvider();
		sorteioProvider = new SorteioProvider();
	}

	public List<Resultado> getResultados(ResultadoFilter filter) {
		List<Resultado> resultados = new ArrayList<>();
		List<Aposta> apostas = apostaProvider.getApostas(filter);
		Optional<Sorteio> optional = getOptional(filter);
		if (optional.isPresent()) {
			Sorteio sorteio = optional.get();
			for (Aposta aposta : apostas) {
				resultados.add(getResultado(aposta, sorteio));
			}
		}
		return resultados;
	}

	private Optional<Sorteio> getOptional(ResultadoFilter filter) {
		return sorteioProvider.getSorteioDoSite(filter);
	}

	private Resultado getResultado(Aposta aposta, Sorteio sorteio) {
		Resultado resultado = new Resultado();
		resultado.setDtSorteio(sorteio.getDtSorteio());
		resultado.setIdConcurso(sorteio.getIdConcurso());
		for (Dezena dezenaAposta : aposta.getDezenas()) {
			boolean foiSorteada = sorteio.getDezenas().contains(dezenaAposta);
			dezenaAposta.setFoiSorteada(foiSorteada);
			if (foiSorteada) {
				resultado.setNuAcertos(resultado.getNuAcertos() + 1);
			}
			resultado.getDezenas().add(dezenaAposta);
		}
		resultado.setVlPremio(getVlPremio(resultado, sorteio));
		return resultado;
	}

	// lotofacil 15 acertos
	// megasena SENA, QUINA QUADRA
	// timania 7 acertos, Time do Coração
	// lotomania 20 acertos
	// duplasena SENA
	// dia de sorte 7 acertos
	private String getVlPremio(Resultado resultado, Sorteio sorteio) {
		Map<String, String> mapFaixas = mapFaixas(sorteio.getPremios());
		String faixa = String.format("%s acertos", resultado.getNuAcertos());
		String vlPremio = mapFaixas.get(faixa);
		if (vlPremio != null) {
			return "R$ " + vlPremio;
		}
		return "R$ 0,00";
	}

	private Map<String, String> mapFaixas(List<Premio> premios) {
		Map<String, String> map = new HashMap<>();
		for (Premio premio : premios) {
			map.put(premio.getFaixa(), premio.getPremio());
		}
		return map;
	}
}
