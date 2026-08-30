package br.com.avsouza7.sortearamigosecreto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SortearService {

	private List<Sorteio> sorteios = new ArrayList<>();

	public void sortear(List<Amigo> amigos) {
		amigos.forEach(a -> sorteios.add(new Sorteio(a, getRandomElement(amigos))));
	}

	public List<Sorteio> getSorteio() {
		return sorteios;
	}

	private Amigo getRandomElement(List<Amigo> amigos) {
		return amigos.get(ThreadLocalRandom.current().nextInt(amigos.size()));
	}

}
