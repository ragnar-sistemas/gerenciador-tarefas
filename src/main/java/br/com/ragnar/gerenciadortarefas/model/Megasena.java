package br.com.ragnar.gerenciadortarefas.model;

import static java.lang.Long.valueOf;

import java.util.ArrayList;
import java.util.List;

public class Megasena {

	private List<Dezena> dezenas;

	public Megasena() {
		dezenas = new ArrayList<>();
		for (int idDezena = 1; idDezena < 61; idDezena++) {
			dezenas.add(new Dezena(valueOf(idDezena)));
		}
	}

	public List<Dezena> getDezenas() {
		return dezenas;
	}

	public void setDezenas(List<Dezena> dezenas) {
		this.dezenas = dezenas;
	}


}
