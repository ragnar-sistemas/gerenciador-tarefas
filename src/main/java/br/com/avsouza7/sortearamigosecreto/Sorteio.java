package br.com.avsouza7.sortearamigosecreto;

public class Sorteio {

	private final Amigo eu;
	private final Amigo meuAmigo;

	public Sorteio(Amigo eu, Amigo meuAmigo) {
		this.eu = eu;
		this.meuAmigo = meuAmigo;
	}

	public Amigo getEu() {
		return eu;
	}

	public Amigo getMeuAmigo() {
		return meuAmigo;
	}

	@Override
	public String toString() {
		return eu.getNome() + " sorteou " + meuAmigo.getNome();
	}

}
