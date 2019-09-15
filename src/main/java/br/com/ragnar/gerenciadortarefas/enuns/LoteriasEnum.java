package br.com.ragnar.gerenciadortarefas.enuns;

public enum LoteriasEnum {
	MEGASENA(1l, "Mega-Sena", DadosLoteriaEnum.MEGASENA),
	LOTOFACIL(2l, "Lotofácil", DadosLoteriaEnum.LOTOFACIL),
	QUINA(3l, "Quina", DadosLoteriaEnum.QUINA), 
	/*LOTOMANIA(4l, "Lotomania", DadosLoteriaEnum.LOTOMANIA),
	TIMEMANIA(5l, "Timemania", DadosLoteriaEnum.TIMEMANIA),
	DUPLA_SENA(6l, "Dupla Sena", DadosLoteriaEnum.DUPLA_SENA),
	DIA_DE_SORTE(7l, "Dia de Sorte", DadosLoteriaEnum.DIA_DE_SORTE)*/;

	private final Long idLoteria;
	private final String nome;
	private final DadosLoteriaEnum dadosLoteriaEnum;

	private LoteriasEnum(Long idLoteria, String nome, DadosLoteriaEnum dadosLoteriaEnum) {
		this.idLoteria = idLoteria;
		this.nome = nome;
		this.dadosLoteriaEnum = dadosLoteriaEnum;
	}

	public Long getIdLoteria() {
		return idLoteria;
	}

	public String getNome() {
		return nome;
	}

	public static LoteriasEnum getById(Long idLoteria) {
		for (LoteriasEnum e : values()) {
			if (e.idLoteria.equals(idLoteria)) {
				return e;
			}
		}
		throw new IllegalArgumentException(String.format("Não existe uma loteira com o id %s", idLoteria));
	}

	public DadosLoteriaEnum getDadosLoteriaEnum() {
		return dadosLoteriaEnum;
	}
}
