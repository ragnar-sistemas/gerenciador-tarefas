package br.com.ragnar.gerenciadortarefas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ragnar.gerenciadortarefas.filter.ResultadoFilter;
import br.com.ragnar.gerenciadortarefas.model.Contador;
import br.com.ragnar.gerenciadortarefas.provider.SorteioProvider;

@Service
public class ContadorDezenasService {

	private SorteioProvider sorteioProvider;
	private List<Contador> contador;

	public ContadorDezenasService() {
		sorteioProvider = new SorteioProvider();
		contador = new ArrayList<>();
	}

	public List<Contador> getContador(ResultadoFilter filter) {

		return contador;
	}

}
