package br.com.avsouza7.provider;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avsouza7.enuns.GrupoEnum;
import br.com.avsouza7.enuns.LoteriaEnum;
import br.com.avsouza7.exceptions.CustomException;
import br.com.avsouza7.filter.ResultadoFilter;
import br.com.avsouza7.model.Agrupador;
import br.com.avsouza7.model.Aposta;
import br.com.avsouza7.model.Dezena;
import br.com.avsouza7.repository.ApostaRepository;
import br.com.avsouza7.util.DateUtil;

@Service
public class ApostaProvider {

  private static final Long ID_APOSTA = 1l;
  // https://www.megasena.com/calendario-de-sorteios
  @Autowired
  private ApostaRepository apostaRepository;

  public List<Aposta> getApostas(ResultadoFilter filter) {
    return apostaRepository.findByIdLoteriaAndIdGrupoAndNuConcurso(filter.getIdLoteria(),
        filter.getIdGrupo(), filter.getIdConcurso());


    // if (LoteriaEnum.LOTOFACIL.equals(LoteriaEnum.getById(filter.getIdLoteria()))) {
    // return getApostas(filter, mapaDeApostasLotoFacil());
    // }
    // if (LoteriaEnum.MEGASENA.equals(LoteriaEnum.getById(filter.getIdLoteria()))) {
    // return getApostas(filter, mapaDeApostasMegaSena());
    // }
    // if (LoteriaEnum.QUINA.equals(LoteriaEnum.getById(filter.getIdLoteria()))) {
    // return getApostas(filter, mapaDeApostasQuina());
    // }
    // throw new CustomException("Não foi implementado uma aposta para o Enum.");
  }

  private List<Aposta> getApostas(ResultadoFilter filter, Map<GrupoEnum, Agrupador> mapaDeApostas) {
    Optional<Agrupador> optionalAgrupador =
        Optional.ofNullable(mapaDeApostas.get(filter.getGrupoEnum()));
    if (optionalAgrupador.isPresent()) {
      Optional<List<Aposta>> apostas =
          Optional.ofNullable(optionalAgrupador.get().getApostas().get(filter.getIdConcurso()));
      if (apostas.isPresent()) {
        return apostas.get();
      }
    }
    throw new CustomException(
        String.format("Nenhuma aposta encontrada para o grupo %s concurso %s da loteria %s.",
            filter.getGrupoEnum().getNmGrupo(), filter.getIdConcurso(),
            LoteriaEnum.getById(filter.getIdLoteria()).getNome()));
  }

  private Map<GrupoEnum, Agrupador> mapaDeApostasLotoFacil() {
    Map<GrupoEnum, Agrupador> apostas = new EnumMap<>(GrupoEnum.class);
    apostas.put(GrupoEnum.SOZINHO, sozinhoLotoFacil());
    apostas.put(GrupoEnum.PAPO_DE_CUNHADO, papoDeCunhadoLotofacil());
    return apostas;
  }

  private Map<GrupoEnum, Agrupador> mapaDeApostasMegaSena() {
    Map<GrupoEnum, Agrupador> apostas = new EnumMap<>(GrupoEnum.class);
    apostas.put(GrupoEnum.PAPO_DE_CUNHADO, papoDeCunhadoMegaSena());
    apostas.put(GrupoEnum.SOZINHO, sozinhoMegaSena());
    apostas.put(GrupoEnum.INIMIGOS_DO_FIM, inimigosDoFimMegaSena());
    apostas.put(GrupoEnum.TORUS_5A_SERIE, torus5aSerieMegaSena());
    apostas.put(GrupoEnum.DIRETORIA, diretoriaMegaSena());
    apostas.put(GrupoEnum.FAMILIA_BUSCAPE, familiaBuscapeMegaSena());
    return apostas;
  }

  private Map<GrupoEnum, Agrupador> mapaDeApostasQuina() {
    Map<GrupoEnum, Agrupador> apostas = new EnumMap<>(GrupoEnum.class);
    apostas.put(GrupoEnum.PAPO_DE_CUNHADO, papoDeCunhadoQuina());
    return apostas;
  }

  private Agrupador sozinhoLotoFacil() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(3287l, DateUtil.convertBr("07/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3288l, DateUtil.convertBr("08/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 7, 8, 9, 15, 16, 18, 19, 20, 22, 23),
            umaApostaLotofacil(2, 5, 6, 9, 10, 11, 12, 13, 14, 15, 17, 19, 21, 24, 25)));
    agrupador.addApostas(3289l, DateUtil.convertBr("09/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3290l, DateUtil.convertBr("10/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3291l, DateUtil.convertBr("11/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3292l, DateUtil.convertBr("13/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3293l, DateUtil.convertBr("14/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3294l, DateUtil.convertBr("15/01/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 8, 9, 12, 13, 16, 19, 22, 23, 25)));
    agrupador.addApostas(3311l, DateUtil.convertBr("04/02/2025"),
        List.of(umaApostaLotofacil(3, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 18, 20, 21, 24),
            umaApostaLotofacil(4, 6, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 21, 24, 25),
            umaApostaLotofacil(2, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 20, 21),
            umaApostaLotofacil(1, 5, 7, 8, 9, 11, 12, 13, 15, 17, 18, 20, 22, 24, 25)));
    agrupador.addApostas(3314l, DateUtil.convertBr("07/02/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 6, 8, 9, 11, 13, 14, 15, 16, 18, 20, 22, 25),
            umaApostaLotofacil(5, 7, 8, 10, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24),
            umaApostaLotofacil(1, 3, 4, 6, 8, 9, 11, 15, 16, 17, 19, 20, 21, 23, 25),
            umaApostaLotofacil(2, 3, 5, 6, 9, 11, 12, 17, 18, 19, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3315l, DateUtil.convertBr("08/02/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 6, 8, 9, 11, 13, 14, 15, 16, 18, 20, 22, 25),
            umaApostaLotofacil(5, 7, 8, 10, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24),
            umaApostaLotofacil(1, 3, 4, 6, 8, 9, 11, 15, 16, 17, 19, 20, 21, 23, 25),
            umaApostaLotofacil(2, 3, 5, 6, 9, 11, 12, 17, 18, 19, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3330l, DateUtil.convertBr("26/02/2025"),
        List.of(umaApostaLotofacil(2, 4, 6, 9, 11, 13, 15, 17, 19, 20, 21, 22, 23, 24, 25),
            umaApostaLotofacil(1, 3, 5, 7, 8, 10, 12, 14, 16, 18, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3334l, DateUtil.convertBr("05/03/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16),
            umaApostaLotofacil(2, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 18, 22, 23, 25),
            umaApostaLotofacil(1, 2, 4, 6, 7, 10, 13, 14, 15, 17, 19, 20, 22, 23, 25)));
    agrupador.addApostas(3335l, DateUtil.convertBr("06/03/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 5, 8, 9, 11, 12, 15, 16, 19, 20, 23, 24, 25),
            umaApostaLotofacil(1, 2, 4, 6, 8, 11, 13, 15, 16, 17, 20, 21, 22, 23, 24)));
    agrupador.addApostas(3336l, DateUtil.convertBr("07/03/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 5, 8, 9, 11, 12, 15, 16, 19, 20, 23, 24, 25),
            umaApostaLotofacil(1, 2, 4, 6, 8, 11, 13, 15, 16, 17, 20, 21, 22, 23, 24)));
    agrupador.addApostas(3338l, DateUtil.convertBr("10/03/2025"),
        List.of(umaApostaLotofacil(2, 4, 5, 7, 8, 9, 10, 13, 14, 16, 17, 19, 20, 23, 24)));
    agrupador.addApostas(3339l, DateUtil.convertBr("11/03/2025"),
        List.of(umaApostaLotofacil(2, 4, 5, 7, 8, 9, 10, 13, 14, 16, 17, 19, 20, 23, 24)));
    agrupador.addApostas(3340l, DateUtil.convertBr("12/03/2025"),
        List.of(umaApostaLotofacil(2, 4, 5, 7, 8, 9, 10, 13, 14, 16, 17, 19, 20, 23, 24)));
    agrupador.addApostas(3341l, DateUtil.convertBr("13/03/2025"),
        List.of(umaApostaLotofacil(1, 4, 6, 8, 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 24),
            umaApostaLotofacil(2, 4, 5, 6, 7, 8, 12, 13, 15, 16, 17, 19, 20, 24, 25),
            umaApostaLotofacil(3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 22, 23, 24),
            umaApostaLotofacil(1, 2, 5, 6, 8, 9, 10, 12, 15, 16, 18, 19, 20, 21, 24),
            umaApostaLotofacil(1, 2, 3, 4, 5, 6, 7, 12, 13, 15, 19, 21, 22, 23, 25),
            umaApostaLotofacil(1, 3, 4, 5, 6, 7, 8, 13, 14, 15, 17, 19, 20, 23, 25)));
    agrupador.addApostas(3342l, DateUtil.convertBr("14/03/2025"),
        List.of(umaApostaLotofacil(1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20, 22),
            umaApostaLotofacil(1, 2, 3, 6, 7, 8, 11, 12, 13, 16, 17, 18, 21, 22, 23)));
    agrupador.addApostas(3345l, DateUtil.convertBr("18/03/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 7, 9, 10, 13, 15, 17, 18, 20, 21, 23, 24)));
    agrupador.addApostas(3346l, DateUtil.convertBr("19/03/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 7, 9, 10, 13, 15, 17, 18, 20, 21, 23, 24)));
    agrupador.addApostas(3347l, DateUtil.convertBr("20/03/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 7, 9, 10, 13, 15, 17, 18, 20, 21, 23, 24)));
    agrupador.addApostas(3348l, DateUtil.convertBr("21/03/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 22, 23, 24, 25),
            umaApostaLotofacil(1, 2, 4, 6, 7, 8, 10, 11, 13, 15, 17, 18, 20, 24, 25),
            umaApostaLotofacil(1, 3, 4, 5, 6, 7, 8, 9, 12, 14, 17, 21, 22, 23, 25),
            umaApostaLotofacil(4, 5, 6, 7, 10, 11, 12, 14, 16, 18, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3351l, DateUtil.convertBr("25/03/2025"),
        List.of(umaApostaLotofacil(2, 4, 5, 6, 8, 9, 10, 11, 12, 13, 16, 17, 20, 21, 24),
            umaApostaLotofacil(3, 4, 5, 6, 7, 10, 11, 13, 14, 16, 18, 20, 21, 24, 25),
            umaApostaLotofacil(1, 2, 3, 5, 6, 9, 11, 12, 14, 15, 16, 20, 21, 24, 25),
            umaApostaLotofacil(1, 4, 6, 7, 8, 9, 11, 12, 13, 16, 18, 19, 20, 23, 25)));
    agrupador.addApostas(3357l, DateUtil.convertBr("01/04/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 8, 10, 12, 13, 15, 17, 19, 20, 21, 22, 24),
            umaApostaLotofacil(1, 3, 5, 7, 9, 10, 11, 12, 13, 18, 20, 21, 23, 24, 25)));
    agrupador.addApostas(3358l, DateUtil.convertBr("02/04/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 8, 10, 12, 13, 15, 17, 19, 20, 21, 22, 24),
            umaApostaLotofacil(1, 3, 5, 7, 9, 10, 11, 12, 13, 18, 20, 21, 23, 24, 25)));
    agrupador.addApostas(3360l, DateUtil.convertBr("04/04/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 7, 8, 10, 11, 12, 13, 15, 18, 19, 20, 21, 23),
            umaApostaLotofacil(1, 3, 4, 5, 6, 7, 8, 9, 13, 16, 17, 18, 20, 21, 25)));
    agrupador.addApostas(3361l, DateUtil.convertBr("05/04/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 7, 8, 10, 11, 12, 13, 15, 18, 19, 20, 21, 23),
            umaApostaLotofacil(1, 3, 4, 5, 6, 7, 8, 9, 13, 16, 17, 18, 20, 21, 25)));
    agrupador.addApostas(3363l, DateUtil.convertBr("08/04/2025"),
        List.of(umaApostaLotofacil(2, 5, 6, 8, 10, 12, 14, 15, 17, 18, 19, 21, 23, 24, 25),
            umaApostaLotofacil(2, 3, 7, 9, 10, 11, 12, 13, 14, 15, 18, 21, 22, 23, 24)));
    agrupador.addApostas(3364l, DateUtil.convertBr("09/04/2025"),
        List.of(umaApostaLotofacil(2, 5, 6, 8, 10, 12, 14, 15, 17, 18, 19, 21, 23, 24, 25),
            umaApostaLotofacil(2, 3, 7, 9, 10, 11, 12, 13, 14, 15, 18, 21, 22, 23, 24)));
    agrupador.addApostas(3365l, DateUtil.convertBr("10/04/2025"),
        List.of(umaApostaLotofacil(2, 3, 5, 7, 9, 10, 12, 15, 18, 19, 21, 22, 23, 24, 25),
            umaApostaLotofacil(2, 3, 5, 6, 7, 8, 11, 14, 18, 19, 21, 22, 23, 24, 25),
            umaApostaLotofacil(2, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21, 22, 23, 24, 25),
            umaApostaLotofacil(2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18)));
    agrupador.addApostas(3367l, DateUtil.convertBr("12/04/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 7, 9, 12, 16, 18, 20, 21, 22, 23, 24, 25),
            umaApostaLotofacil(1, 3, 4, 5, 8, 9, 11, 12, 13, 19, 20, 21, 22, 23, 24),
            umaApostaLotofacil(1, 2, 3, 4, 7, 10, 11, 12, 13, 14, 15, 16, 19, 21, 23),
            umaApostaLotofacil(2, 3, 4, 5, 6, 8, 11, 14, 15, 16, 18, 19, 20, 21, 24),
            umaApostaLotofacil(1, 2, 3, 4, 6, 7, 8, 10, 13, 14, 15, 16, 21, 22, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 14, 16, 21, 23, 24)));
    agrupador.addApostas(3368l, DateUtil.convertBr("14/04/2025"),
        List.of(umaApostaLotofacil(1, 4, 6, 8, 10, 12, 14, 15, 17, 18, 20, 22, 23, 24, 25),
            umaApostaLotofacil(1, 3, 5, 6, 7, 10, 13, 14, 15, 17, 19, 21, 23, 24, 25)));
    agrupador.addApostas(3369l, DateUtil.convertBr("15/04/2025"),
        List.of(umaApostaLotofacil(1, 4, 6, 8, 10, 12, 14, 15, 17, 18, 20, 22, 23, 24, 25),
            umaApostaLotofacil(1, 3, 5, 6, 7, 10, 13, 14, 15, 17, 19, 21, 23, 24, 25)));
    agrupador.addApostas(3370l, DateUtil.convertBr("16/04/2025"),
        List.of(umaApostaLotofacil(1, 4, 6, 8, 10, 12, 14, 15, 17, 18, 20, 22, 23, 24, 25),
            umaApostaLotofacil(1, 3, 5, 6, 7, 10, 13, 14, 15, 17, 19, 21, 23, 24, 25)));
    agrupador.addApostas(3374l, DateUtil.convertBr("23/04/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 7, 8, 9, 11, 13, 14, 15, 17, 19, 21, 23, 25),
            umaApostaLotofacil(7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 23, 24),
            umaApostaLotofacil(2, 3, 5, 7, 10, 11, 13, 14, 16, 19, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3375l, DateUtil.convertBr("24/04/2025"),
        List.of(umaApostaLotofacil(1, 3, 5, 7, 8, 9, 11, 13, 14, 15, 17, 19, 21, 23, 25),
            umaApostaLotofacil(7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 23, 24),
            umaApostaLotofacil(2, 3, 5, 7, 10, 11, 13, 14, 16, 19, 20, 21, 22, 23, 25)));
    agrupador.addApostas(3376l, DateUtil.convertBr("25/04/2025"),
        List.of(umaApostaLotofacil(2, 5, 6, 8, 10, 12, 13, 14, 16, 17, 18, 19, 20, 23, 24),
            umaApostaLotofacil(1, 2, 3, 4, 9, 11, 12, 13, 14, 15, 17, 20, 21, 22, 25),
            umaApostaLotofacil(5, 6, 7, 9, 11, 12, 13, 15, 16, 19, 20, 21, 23, 24, 25)));
    agrupador.addApostas(3377l, DateUtil.convertBr("26/04/2025"),
        List.of(umaApostaLotofacil(2, 5, 6, 8, 10, 12, 13, 14, 16, 17, 18, 19, 20, 23, 24),
            umaApostaLotofacil(1, 2, 3, 4, 9, 11, 12, 13, 14, 15, 17, 20, 21, 22, 25),
            umaApostaLotofacil(5, 6, 7, 9, 11, 12, 13, 15, 16, 19, 20, 21, 23, 24, 25)));
    agrupador.addApostas(3384l, DateUtil.convertBr("06/05/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 9, 10, 11, 12, 13, 15, 17, 19, 22, 24, 25),
            umaApostaLotofacil(1, 2, 5, 7, 8, 10, 11, 13, 15, 18, 20, 21, 23, 24, 25),
            umaApostaLotofacil(2, 4, 5, 6, 7, 8, 9, 10, 12, 13, 16, 17, 18, 24, 25)));
    agrupador.addApostas(3385l, DateUtil.convertBr("07/05/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 9, 10, 11, 12, 13, 15, 17, 19, 22, 24, 25),
            umaApostaLotofacil(1, 2, 5, 7, 8, 10, 11, 13, 15, 18, 20, 21, 23, 24, 25),
            umaApostaLotofacil(2, 4, 5, 6, 7, 8, 9, 10, 12, 13, 16, 17, 18, 24, 25)));
    agrupador.addApostas(3386l, DateUtil.convertBr("08/05/2025"),
        List.of(umaApostaLotofacil(1, 4, 5, 6, 10, 11, 12, 13, 17, 18, 21, 22, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 4, 9, 10, 11, 12, 14, 17, 19, 21, 22, 23, 25)));
    agrupador.addApostas(3387l, DateUtil.convertBr("09/05/2025"),
        List.of(umaApostaLotofacil(1, 4, 5, 6, 10, 11, 12, 13, 17, 18, 21, 22, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 4, 9, 10, 11, 12, 14, 17, 19, 21, 22, 23, 25)));
    agrupador.addApostas(3388l, DateUtil.convertBr("10/05/2025"),
        List.of(umaApostaLotofacil(1, 4, 5, 6, 10, 11, 12, 13, 17, 18, 21, 22, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 4, 9, 10, 11, 12, 14, 17, 19, 21, 22, 23, 25)));
    agrupador.addApostas(3389l, DateUtil.convertBr("12/05/2025"),
        List.of(umaApostaLotofacil(1, 2, 3, 4, 7, 9, 11, 12, 13, 15, 16, 17, 20, 21, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 8, 9, 12, 13, 15, 19, 21, 22, 23, 24)));
    agrupador.addApostas(3390l, DateUtil.convertBr("13/05/2025"),
        List.of(umaApostaLotofacil(1, 2, 4, 5, 7, 8, 10, 13, 14, 17, 18, 19, 21, 22, 25),
            umaApostaLotofacil(1, 2, 4, 5, 6, 8, 9, 11, 13, 14, 16, 19, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 4, 6, 8, 9, 11, 12, 16, 17, 18, 20, 21, 25),
            umaApostaLotofacil(1, 3, 4, 6, 9, 10, 11, 14, 15, 16, 18, 21, 22, 23, 25),
            umaApostaLotofacil(2, 3, 4, 6, 7, 8, 9, 10, 12, 16, 21, 22, 23, 24, 25),
            umaApostaLotofacil(2, 4, 6, 7, 8, 9, 11, 13, 16, 17, 19, 20, 21, 23, 24),
            umaApostaLotofacil(1, 2, 3, 5, 8, 10, 14, 17, 18, 19, 20, 21, 22, 23, 24),
            umaApostaLotofacil(3, 4, 5, 9, 10, 11, 12, 14, 15, 17, 19, 20, 21, 23, 24)));
    agrupador.addApostas(3398l, DateUtil.convertBr("22/05/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25),
            umaApostaLotofacil(1, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 21, 22, 23, 25),
            umaApostaLotofacil(1, 2, 3, 7, 8, 10, 11, 14, 15, 16, 17, 18, 20, 21, 24)));
    agrupador.addApostas(3399l, DateUtil.convertBr("23/05/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25),
            umaApostaLotofacil(1, 8, 9, 10, 11, 12, 13, 14, 16, 18, 19, 21, 22, 23, 25),
            umaApostaLotofacil(1, 2, 3, 7, 8, 10, 11, 14, 15, 16, 17, 18, 20, 21, 24)));

    agrupador.addApostas(3426, DateUtil.convertBr("25/06/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3427, DateUtil.convertBr("26/06/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3428, DateUtil.convertBr("27/06/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3429, DateUtil.convertBr("28/06/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3430, DateUtil.convertBr("30/06/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3431, DateUtil.convertBr("01/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3432, DateUtil.convertBr("02/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3433, DateUtil.convertBr("03/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3434, DateUtil.convertBr("04/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3435, DateUtil.convertBr("07/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3436, DateUtil.convertBr("08/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3437, DateUtil.convertBr("09/07/2025"),
        List.of(umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 15, 17, 18, 20, 21, 24, 25)));

    agrupador.addApostas(3462, DateUtil.convertBr("06/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3463, DateUtil.convertBr("07/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3464, DateUtil.convertBr("08/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3465, DateUtil.convertBr("09/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3466, DateUtil.convertBr("11/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3467, DateUtil.convertBr("12/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3468, DateUtil.convertBr("13/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3469, DateUtil.convertBr("14/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));
    agrupador.addApostas(3470, DateUtil.convertBr("15/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 4, 5, 6, 8, 10, 13, 15, 17, 18, 20, 21, 24, 25)));

    agrupador.addApostas(3472, DateUtil.convertBr("18/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 7, 10, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25)));
    agrupador.addApostas(3473, DateUtil.convertBr("19/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 7, 10, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25)));
    agrupador.addApostas(3474, DateUtil.convertBr("20/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 7, 10, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25)));
    agrupador.addApostas(3475, DateUtil.convertBr("21/08/2025"),
        List.of(umaApostaLotofacil(2, 3, 7, 10, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25)));
    agrupador.addApostas(3480, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaLotofacil(3, 5, 6, 7, 8, 9, 10, 12, 14, 15, 18, 20, 21, 23, 24),
            umaApostaLotofacil(1, 5, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 23, 25)));

    agrupador.addApostas(3481, DateUtil.convertBr("08/09/2025"),
        List.of(umaApostaLotofacil(1, 3, 4, 7, 8, 9, 11, 13, 16, 18, 20, 21, 23, 24),
            umaApostaLotofacil(1, 4, 5, 6, 8, 10, 12, 13, 18, 19, 21, 22, 23, 24, 25),
            umaApostaLotofacil(2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 16, 17, 19, 22)));

    agrupador.addApostas(3493, DateUtil.convertBr("22/09/2025"),
        List.of(umaApostaLotofacil(4, 5, 6, 10, 11, 12, 14, 16, 18, 19, 20, 21, 22, 24, 25),
            umaApostaLotofacil(1, 4, 5, 6, 8, 9, 10, 12, 13, 14, 15, 22, 23, 24, 25)));

    return agrupador;
  }

  private Agrupador papoDeCunhadoLotofacil() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(3190l, DateUtil.convertBr("09/09/2024"),
        List.of(umaApostaLotofacil(1, 2, 3, 6, 8, 9, 10, 13, 15, 16, 17, 18, 21, 23, 25),
            umaApostaLotofacil(1, 3, 4, 7, 8, 9, 12, 13, 14, 16, 17, 21, 22, 24, 25),
            umaApostaLotofacil(1, 2, 3, 5, 9, 10, 11, 12, 13, 14, 16, 18, 22, 23, 25),
            umaApostaLotofacil(2, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 18, 20, 24),
            umaApostaLotofacil(3, 4, 5, 6, 8, 9, 10, 13, 14, 16, 19, 20, 22, 24, 25),
            umaApostaLotofacil(1, 2, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19),
            umaApostaLotofacil(1, 2, 3, 4, 5, 8, 10, 12, 18, 19, 20, 21, 22, 24, 25),
            umaApostaLotofacil(1, 2, 4, 5, 6, 8, 10, 14, 15, 16, 17, 21, 22, 23, 25),
            umaApostaLotofacil(1, 2, 3, 7, 8, 9, 11, 13, 15, 16, 17, 18, 19, 20, 25),
            umaApostaLotofacil(2, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 25),
            umaApostaLotofacil(1, 2, 3, 6, 8, 10, 12, 13, 14, 16, 17, 18, 19, 23, 25),
            umaApostaLotofacil(1, 2, 6, 7, 8, 10, 13, 14, 17, 18, 20, 21, 22, 23, 24),
            umaApostaLotofacil(1, 2, 3, 4, 5, 9, 11, 12, 13, 17, 20, 22, 23, 24, 25),
            umaApostaLotofacil(1, 4, 7, 8, 9, 10, 11, 12, 15, 16, 17, 20, 22, 24, 25),
            umaApostaLotofacil(2, 4, 6, 9, 10, 11, 12, 13, 15, 17, 19, 20, 21, 23, 25),
            umaApostaLotofacil(2, 6, 7, 8, 9, 10, 13, 16, 17, 18, 20, 21, 22, 23, 25),
            umaApostaLotofacil(1, 2, 5, 7, 8, 9, 12, 15, 16, 17, 19, 20, 22, 23, 25),
            umaApostaLotofacil(3, 5, 7, 9, 10, 12, 13, 16, 17, 19, 20, 21, 22, 23, 25),
            umaApostaLotofacil(1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17, 21),
            umaApostaLotofacil(2, 4, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 22, 23),
            umaApostaLotofacil(2, 3, 4, 5, 6, 9, 12, 13, 14, 15, 16, 20, 21, 23, 24),
            umaApostaLotofacil(2, 3, 4, 6, 10, 12, 14, 15, 316, 19, 20, 21, 22, 23, 25),
            umaApostaLotofacil(2, 7, 8, 9, 10, 11, 13, 14, 15, 17, 19, 20, 23, 24, 25),
            umaApostaLotofacil(1, 3, 4, 6, 7, 9, 10, 11, 13, 14, 16, 17, 18, 22, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 7, 10, 14, 15, 16, 18, 19, 21, 22, 25),
            umaApostaLotofacil(2, 4, 7, 8, 9, 10, 14, 16, 18, 19, 20, 21, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 22, 25),
            umaApostaLotofacil(1, 2, 3, 8, 10, 11, 12, 14, 15, 17, 19, 20, 21, 22, 25),
            umaApostaLotofacil(1, 2, 3, 4, 6, 7, 9, 10, 14, 15, 16, 17, 18, 20, 25),
            umaApostaLotofacil(2, 3, 5, 6, 9, 10, 12, 15, 16, 18, 20, 21, 22, 23, 25),
            umaApostaLotofacil(1, 3, 4, 6, 9, 11, 12, 13, 15, 17, 18, 19, 22, 24, 25),
            umaApostaLotofacil(2, 3, 4, 6, 8, 13, 14, 15, 18, 19, 20, 21, 23, 24, 25),
            umaApostaLotofacil(1, 2, 3, 4, 7, 8, 11, 12, 14, 16, 17, 18, 19, 20, 23),
            umaApostaLotofacil(3, 4, 5, 6, 7, 8, 10, 12, 14, 15, 17, 18, 21, 22, 25),
            umaApostaLotofacil(1, 3, 4, 5, 6, 7, 8, 11, 13, 18, 20, 21, 22, 24, 25),
            umaApostaLotofacil(1, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 18, 20, 22, 23),
            umaApostaLotofacil(1, 2, 3, 5, 6, 7, 9, 11, 12, 13, 14, 19, 22, 23, 24),
            umaApostaLotofacil(2, 3, 4, 7, 8, 10, 13, 14, 15, 16, 17, 21, 22, 23, 24),
            umaApostaLotofacil(1, 4, 5, 6, 8, 10, 14, 15, 19, 20, 21, 22, 23, 24, 25),
            umaApostaLotofacil(4, 5, 7, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 25),
            umaApostaLotofacil(2, 3, 4, 5, 6, 7, 9, 13, 15, 17, 18, 19, 20, 21, 22),
            umaApostaLotofacil(1, 2, 5, 6, 7, 8, 9, 10, 13, 18, 19, 20, 21, 24, 25),
            umaApostaLotofacil(3, 4, 6, 8, 9, 11, 12, 13, 14, 15, 16, 17, 20, 22, 24),
            umaApostaLotofacil(1, 2, 3, 5, 6, 8, 10, 11, 12, 13, 15, 18, 20, 23, 24),
            umaApostaLotofacil(2, 3, 6, 7, 8, 9, 10, 13, 15, 17, 19, 20, 21, 22, 23),
            umaApostaLotofacil(1, 2, 3, 7, 8, 9, 11, 12, 14, 16, 18, 20, 22, 24, 25),
            umaApostaLotofacil(3, 5, 7, 9, 10, 12, 13, 15, 16, 17, 18, 19, 21, 23, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 16, 17, 18, 21),
            umaApostaLotofacil(1, 3, 4, 5, 7, 9, 10, 11, 12, 14, 15, 20, 22, 2, 25),
            umaApostaLotofacil(1, 2, 3, 4, 5, 7, 9, 11, 13, 16, 17, 18, 19, 22, 24)));
    return agrupador;
  }

  private Agrupador papoDeCunhadoMegaSena() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(2830l, DateUtil.convertBr("18/02/2025"),
        List.of(umaApostaMegaSena(14, 25, 28, 39, 48, 59), umaApostaMegaSena(3, 16, 33, 50, 53, 55),
            umaApostaMegaSena(16, 33, 34, 35, 36, 46), umaApostaMegaSena(11, 21, 25, 47, 58, 60),
            umaApostaMegaSena(3, 9, 25, 34, 49, 55), umaApostaMegaSena(12, 16, 17, 33, 36, 47),
            umaApostaMegaSena(17, 20, 24, 25, 40, 41), umaApostaMegaSena(7, 14, 22, 37, 41, 59),
            umaApostaMegaSena(1, 8, 14, 43, 44, 47), umaApostaMegaSena(12, 20, 27, 35, 39, 51),
            umaApostaMegaSena(3, 18, 28, 34, 44, 59), umaApostaMegaSena(30, 41, 46, 48, 49, 50),
            umaApostaMegaSena(1, 7, 11, 41, 48, 50), umaApostaMegaSena(9, 11, 27, 32, 33, 34),
            umaApostaMegaSena(3, 14, 17, 20, 39, 41), umaApostaMegaSena(8, 10, 15, 38, 39, 57),
            umaApostaMegaSena(22, 29, 38, 41, 47, 54), umaApostaMegaSena(11, 13, 43, 44, 45, 56),
            umaApostaMegaSena(2, 6, 24, 36, 44, 54), umaApostaMegaSena(4, 21, 26, 37, 48, 50),
            umaApostaMegaSena(5, 20, 21, 23, 28, 55), umaApostaMegaSena(8, 23, 32, 37, 47, 54),
            umaApostaMegaSena(26, 31, 43, 46, 48, 58), umaApostaMegaSena(2, 15, 39, 40, 54, 55),
            umaApostaMegaSena(12, 25, 37, 46, 50, 56), umaApostaMegaSena(4, 15, 20, 32, 55, 60),
            umaApostaMegaSena(3, 4, 12, 35, 42, 59), umaApostaMegaSena(3, 15, 25, 33, 37, 39),
            umaApostaMegaSena(2, 9, 16, 17, 26, 38), umaApostaMegaSena(14, 17, 22, 44, 53, 59)));
    agrupador.addApostas(2831l, DateUtil.convertBr("20/02/2025"),
        List.of(umaApostaMegaSena(13, 16, 23, 27, 35, 41), umaApostaMegaSena(7, 8, 12, 27, 44, 45),
            umaApostaMegaSena(3, 6, 25, 32, 49, 53), umaApostaMegaSena(3, 43, 45, 51, 55, 58),
            umaApostaMegaSena(9, 12, 19, 21, 24, 53), umaApostaMegaSena(2, 10, 15, 18, 49, 52),
            umaApostaMegaSena(9, 21, 32, 51, 53, 60), umaApostaMegaSena(10, 14, 31, 45, 54, 58),
            umaApostaMegaSena(11, 15, 19, 31, 36, 48), umaApostaMegaSena(8, 12, 20, 28, 36, 43),
            umaApostaMegaSena(2, 11, 12, 15, 42, 43), umaApostaMegaSena(9, 29, 30, 35, 48, 51),
            umaApostaMegaSena(36, 37, 38, 47, 52, 60), umaApostaMegaSena(3, 5, 12, 25, 52, 54),
            umaApostaMegaSena(7, 25, 37, 45, 46, 52), umaApostaMegaSena(3, 8, 29, 40, 41, 42),
            umaApostaMegaSena(2, 7, 17, 39, 43, 47), umaApostaMegaSena(10, 11, 12, 13, 48, 60),
            umaApostaMegaSena(4, 10, 12, 31, 46, 52), umaApostaMegaSena(1, 11, 17, 20, 48, 49),
            umaApostaMegaSena(5, 8, 21, 36, 43, 50), umaApostaMegaSena(2, 9, 16, 17, 26, 38),
            umaApostaMegaSena(2, 7, 25, 33, 49, 52)));
    agrupador.addApostas(2832l, DateUtil.convertBr("21/02/2025"),
        List.of(umaApostaMegaSena(2, 10, 23, 30, 32, 50), umaApostaMegaSena(8, 15, 26, 29, 56, 58),
            umaApostaMegaSena(1, 10, 34, 40, 46, 47), umaApostaMegaSena(12, 17, 19, 30, 33, 48),
            umaApostaMegaSena(17, 26, 32, 39, 42, 46), umaApostaMegaSena(4, 8, 26, 32, 35, 45),
            umaApostaMegaSena(15, 30, 38, 43, 55, 58), umaApostaMegaSena(2, 7, 27, 30, 31, 60),
            umaApostaMegaSena(2, 18, 19, 30, 55, 57), umaApostaMegaSena(16, 20, 22, 27, 33, 57),
            umaApostaMegaSena(1, 10, 19, 22, 35, 54), umaApostaMegaSena(21, 38, 39, 53, 56, 59),
            umaApostaMegaSena(6, 9, 17, 34, 41, 52), umaApostaMegaSena(1, 10, 15, 33, 54, 56),
            umaApostaMegaSena(11, 18, 20, 23, 32, 48), umaApostaMegaSena(10, 15, 18, 43, 45, 57),
            umaApostaMegaSena(14, 16, 19, 28, 37, 48), umaApostaMegaSena(1, 4, 16, 32, 44, 57),
            umaApostaMegaSena(2, 4, 9, 21, 24, 55), umaApostaMegaSena(2, 14, 21, 39, 41, 49),
            umaApostaMegaSena(2, 9, 19, 29, 47, 48)));
    agrupador.addApostas(2833l, DateUtil.convertBr("25/02/2025"),
        List.of(umaApostaMegaSena(2, 26, 30, 55, 57, 58), umaApostaMegaSena(14, 18, 28, 32, 42, 47),
            umaApostaMegaSena(8, 15, 18, 42, 46, 60), umaApostaMegaSena(9, 10, 17, 23, 26, 38),
            umaApostaMegaSena(1, 5, 7, 22, 34, 48), umaApostaMegaSena(5, 7, 31, 45, 52, 60),
            umaApostaMegaSena(7, 9, 12, 15, 16, 25), umaApostaMegaSena(39, 45, 46, 48, 53, 59),
            umaApostaMegaSena(3, 13, 25, 43, 54, 58), umaApostaMegaSena(3, 9, 20, 24, 43, 51),
            umaApostaMegaSena(15, 30, 34, 35, 38, 58), umaApostaMegaSena(3, 5, 23, 30, 46, 54),
            umaApostaMegaSena(5, 9, 14, 20, 48, 59), umaApostaMegaSena(3, 28, 42, 46, 56, 57),
            umaApostaMegaSena(1, 26, 31, 32, 50, 55), umaApostaMegaSena(17, 20, 38, 45, 48, 50),
            umaApostaMegaSena(5, 10, 13, 35, 41, 53), umaApostaMegaSena(10, 17, 18, 23, 57, 60),
            umaApostaMegaSena(1, 29, 30, 33, 55, 57), umaApostaMegaSena(5, 8, 21, 36, 43, 50),
            umaApostaMegaSena(2, 9, 16, 17, 26, 38), umaApostaMegaSena(2, 7, 25, 33, 49, 52),
            umaApostaMegaSena(11, 12, 24, 37, 45, 54), umaApostaMegaSena(14, 18, 25, 40, 43, 56)));
    agrupador.addApostas(2845l, DateUtil.convertBr("27/03/2025"),
        List.of(umaApostaMegaSena(25, 28, 33, 39, 46, 48),
            umaApostaMegaSena(19, 26, 35, 44, 48, 58), umaApostaMegaSena(1, 2, 5, 27, 41, 47),
            umaApostaMegaSena(2, 11, 12, 18, 53, 57), umaApostaMegaSena(1, 3, 5, 26, 36, 58),
            umaApostaMegaSena(17, 21, 44, 49, 51, 54), umaApostaMegaSena(11, 28, 29, 34, 42, 48),
            umaApostaMegaSena(5, 8, 21, 36, 43, 50), umaApostaMegaSena(1, 5, 7, 29, 33, 41),
            umaApostaMegaSena(2, 9, 16, 17, 26, 38)));
    agrupador.addApostas(2848l, DateUtil.convertBr("03/04/2025"),
        List.of(umaApostaMegaSena(4, 25, 26, 36, 42, 48), umaApostaMegaSena(9, 19, 36, 43, 45, 54),
            umaApostaMegaSena(26, 31, 35, 47, 55, 59), umaApostaMegaSena(3, 18, 38, 40, 42, 43),
            umaApostaMegaSena(1, 14, 24, 31, 40, 53), umaApostaMegaSena(2, 16, 25, 28, 42, 57),
            umaApostaMegaSena(4, 14, 26, 31, 44, 60), umaApostaMegaSena(29, 49, 53, 54, 58, 59),
            umaApostaMegaSena(2, 9, 16, 17, 26, 38), umaApostaMegaSena(5, 8, 21, 36, 43, 50),
            umaApostaMegaSena(1, 31, 37, 44, 48, 53), umaApostaMegaSena(1, 3, 8, 38, 58, 60)));
    agrupador.addApostas(2849l, DateUtil.convertBr("05/04/2025"),
        List.of(umaApostaMegaSena(10, 22, 35, 48, 52, 57),
            umaApostaMegaSena(15, 18, 19, 21, 33, 41), umaApostaMegaSena(14, 28, 31, 42, 55, 59),
            umaApostaMegaSena(7, 10, 17, 27, 56, 58), umaApostaMegaSena(5, 6, 7, 12, 38, 58),
            umaApostaMegaSena(1, 2, 3, 23, 38, 52), umaApostaMegaSena(32, 41, 43, 47, 51, 59),
            umaApostaMegaSena(7, 11, 14, 25, 29, 41), umaApostaMegaSena(20, 21, 22, 30, 33, 43),
            umaApostaMegaSena(1, 10, 13, 26, 35, 40, 49, 58),
            umaApostaMegaSena(9, 21, 25, 34, 35, 39, 57, 58)));
    agrupador.addApostas(2850l, DateUtil.convertBr("08/04/2025"),
        List.of(umaApostaMegaSena(4, 11, 37, 45, 54, 60), umaApostaMegaSena(5, 6, 12, 15, 30, 32),
            umaApostaMegaSena(4, 5, 32, 47, 52, 57), umaApostaMegaSena(17, 25, 44, 47, 51, 55),
            umaApostaMegaSena(2, 9, 25, 27, 52, 56), umaApostaMegaSena(1, 6, 49, 54, 55, 57),
            umaApostaMegaSena(19, 25, 31, 34, 36, 37), umaApostaMegaSena(2, 6, 9, 18, 25, 40),
            umaApostaMegaSena(9, 10, 28, 40, 42, 44, 45, 57),
            umaApostaMegaSena(3, 14, 16, 19, 23, 28, 39, 54)));
    agrupador.addApostas(2865l, DateUtil.convertBr("20/05/2025"),
        List.of(umaApostaMegaSena(24, 30, 32, 33, 35, 42),
            umaApostaMegaSena(25, 33, 36, 42, 45, 54), umaApostaMegaSena(7, 10, 11, 24, 51, 52),
            umaApostaMegaSena(1, 4, 17, 24, 29, 46), umaApostaMegaSena(6, 16, 36, 46, 47, 49),
            umaApostaMegaSena(2, 3, 13, 34, 44, 60), umaApostaMegaSena(3, 32, 38, 46, 55, 56),
            umaApostaMegaSena(12, 18, 31, 34, 41, 53), umaApostaMegaSena(8, 16, 31, 36, 39, 56),
            umaApostaMegaSena(12, 27, 32, 34, 46, 48), umaApostaMegaSena(2, 9, 16, 17, 26, 38),
            umaApostaMegaSena(6, 24, 30, 35, 49, 57), umaApostaMegaSena(2, 3, 7, 9, 11, 38),
            umaApostaMegaSena(5, 8, 21, 36, 43, 50), umaApostaMegaSena(5, 14, 21, 31, 46, 53),
            umaApostaMegaSena(5, 16, 20, 28, 53, 56)));
    agrupador.addApostas(2867l, DateUtil.convertBr("24/05/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2868l, DateUtil.convertBr("27/05/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2869l, DateUtil.convertBr("29/05/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2870l, DateUtil.convertBr("31/05/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2871l, DateUtil.convertBr("03/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2872l, DateUtil.convertBr("05/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2873l, DateUtil.convertBr("07/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2874l, DateUtil.convertBr("10/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2875l, DateUtil.convertBr("12/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2876l, DateUtil.convertBr("14/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2877l, DateUtil.convertBr("17/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2878l, DateUtil.convertBr("21/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2879l, DateUtil.convertBr("24/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2880l, DateUtil.convertBr("26/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2881l, DateUtil.convertBr("28/06/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2882l, DateUtil.convertBr("01/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2883l, DateUtil.convertBr("03/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2884l, DateUtil.convertBr("05/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2885l, DateUtil.convertBr("08/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2886l, DateUtil.convertBr("10/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2887l, DateUtil.convertBr("12/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2888l, DateUtil.convertBr("15/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2889l, DateUtil.convertBr("17/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2890l, DateUtil.convertBr("19/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2891l, DateUtil.convertBr("22/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2892l, DateUtil.convertBr("24/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2893l, DateUtil.convertBr("24/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2894l, DateUtil.convertBr("29/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2895l, DateUtil.convertBr("31/07/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2896l, DateUtil.convertBr("02/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2897l, DateUtil.convertBr("05/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2898l, DateUtil.convertBr("07/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2899l, DateUtil.convertBr("09/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2900l, DateUtil.convertBr("12/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2901l, DateUtil.convertBr("14/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2902l, DateUtil.convertBr("16/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2903l, DateUtil.convertBr("12/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2904l, DateUtil.convertBr("12/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2905l, DateUtil.convertBr("12/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2906l, DateUtil.convertBr("26/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2907l, DateUtil.convertBr("29/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2908l, DateUtil.convertBr("31/08/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2909l, DateUtil.convertBr("02/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2910l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2911l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2912l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2913l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2914l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2915l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2916l, DateUtil.convertBr("06/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2917l, DateUtil.convertBr("18/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2918l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2919l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2920l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2921l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2922l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2923l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2924l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2925l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2926l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2927l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2928l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2929l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2931l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2932l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2933l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2934l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2935l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2936l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2937l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2938l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2939l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2940l, DateUtil.convertBr("13/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2941l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2942l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2943l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2944l, DateUtil.convertBr("23/09/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2945l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2946l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2947l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2948l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2949l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2950l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2951l, DateUtil.convertBr("16/12/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2952l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2953l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    agrupador.addApostas(2954l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2955l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2956l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));
    agrupador.addApostas(2957l, DateUtil.convertBr("29/11/2025"),
        List.of(umaApostaMegaSena(1, 3, 4, 10, 11, 24), umaApostaMegaSena(2, 6, 9, 11, 17, 29),
            umaApostaMegaSena(8, 19, 21, 42, 49, 50)));

    return agrupador;
  }

  private Agrupador sozinhoMegaSena() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(2833l, DateUtil.convertBr("25/02/2025"),
        List.of(umaApostaMegaSena(7, 23, 26, 27, 32, 34, 53, 57, 59)));
    return agrupador;
  }

  private Agrupador inimigosDoFimMegaSena() {
    Agrupador agrupador = new Agrupador();
    return agrupador;
  }

  private Agrupador torus5aSerieMegaSena() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(2832l, DateUtil.convertBr("21/02/2025"), List.of(
        umaApostaMegaSena(8, 17, 26, 29, 42, 50, 55), umaApostaMegaSena(1, 3, 8, 9, 10, 17, 20)));
    agrupador.addApostas(2833l, DateUtil.convertBr("25/02/2025"),
        List.of(umaApostaMegaSena(29, 31, 35, 38, 45, 60),
            umaApostaMegaSena(12, 18, 40, 42, 48, 57, 59),
            umaApostaMegaSena(14, 18, 24, 30, 31, 33, 48)));
    return agrupador;
  }

  private Agrupador diretoriaMegaSena() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(2832l, DateUtil.convertBr("21/02/2025"), List.of(
        umaApostaMegaSena(8, 13, 15, 45, 46, 50), umaApostaMegaSena(7, 11, 25, 31, 41, 54, 60)));
    agrupador.addApostas(2833l, DateUtil.convertBr("25/02/2025"),
        List.of(umaApostaMegaSena(5, 7, 14, 21, 23, 28, 56),
            umaApostaMegaSena(5, 20, 23, 25, 28, 50), umaApostaMegaSena(25, 36, 38, 48, 52, 60),
            umaApostaMegaSena(4, 9, 20, 41, 43, 52)));
    return agrupador;
  }

  private Agrupador familiaBuscapeMegaSena() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(2810l, DateUtil.convertBr("31/12/2024"),
        List.of(umaApostaMegaSena(3, 11, 19, 28, 34, 41, 49, 56),
            umaApostaMegaSena(3, 7, 14, 19, 25, 36, 48, 54)));
    return agrupador;
  }

  private Agrupador papoDeCunhadoQuina() {
    Agrupador agrupador = new Agrupador();
    agrupador.addApostas(6529l, DateUtil.convertBr("10/09/2024"),
        List.of(umaApostaQuina(1, 17, 22, 52, 74), umaApostaQuina(15, 24, 56, 67, 80)));
    agrupador.addApostas(6760l, DateUtil.convertBr("28/06/2025"),
        List.of(umaApostaQuina(9, 11, 12, 14, 31, 47, 50, 65, 80),
            umaApostaQuina(24, 27, 41, 42, 46, 70, 71, 74, 75),
            umaApostaQuina(11, 13, 23, 28, 40, 49, 51, 53, 56),
            umaApostaQuina(9, 19, 22, 27, 31, 40, 50, 58, 78),
            umaApostaQuina(32, 35, 36, 37, 42, 46, 49, 65, 76),
            umaApostaQuina(4, 9, 21, 22, 36, 41, 42, 58, 62, 63)));
    return agrupador;
  }

  private Aposta umaApostaLotofacil(int... dezenas) {
    return umaAposta(LoteriaEnum.LOTOFACIL, dezenas);
  }

  private Aposta umaApostaMegaSena(int... dezenas) {
    return umaAposta(LoteriaEnum.MEGASENA, dezenas);
  }

  private Aposta umaApostaQuina(int... dezenas) {
    return umaAposta(LoteriaEnum.QUINA, dezenas);
  }

  private Aposta umaAposta(LoteriaEnum loteria, int... dezenas) {
    Aposta aposta = new Aposta();
    aposta.setIdAposta(ID_APOSTA);
    aposta.setIdLoteria(loteria.getIdLoteria());
    for (int i = 0; i < dezenas.length; i++) {
      aposta.getDezenas().add(umaDezena(Long.valueOf(dezenas[i])));
    }
    return aposta;
  }

  private Dezena umaDezena(Long nuDezena) {
    Dezena dezena = new Dezena();
    dezena.setNuDezena(nuDezena);
    return dezena;
  }
}
