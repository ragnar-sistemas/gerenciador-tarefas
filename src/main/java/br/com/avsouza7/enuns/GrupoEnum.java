package br.com.avsouza7.enuns;

public enum GrupoEnum {
    SOZINHO(1l, "Somente Eu"), PAPO_DE_CUNHADO(2L, "Papo de Cunhado"), TORUS_5A_SERIE(3L, "Torus 5a Série"),
    DIRETORIA(4l, "Diretoria"), INIMIGOS_DO_FIM(5l, "Inimigos do fim"), FAMILIA_BUSCAPE(6L, "A Família Buscapé"),
    LARGADOS_PELADOS(7L, "Largados pelados");

    private final Long idGrupo;
    private final String nmGrupo;

    private GrupoEnum(Long idGrupo, String nmGrupo) {
	this.idGrupo = idGrupo;
	this.nmGrupo = nmGrupo;
    }

    public Long getIdGrupo() {
	return idGrupo;
    }

    public String getNmGrupo() {
	return nmGrupo;
    }

    public static GrupoEnum getById(Long idGrupo) {
	for (GrupoEnum e : values()) {
	    if (e.idGrupo.equals(idGrupo)) {
		return e;
	    }
	}
	return SOZINHO;
    }

}
