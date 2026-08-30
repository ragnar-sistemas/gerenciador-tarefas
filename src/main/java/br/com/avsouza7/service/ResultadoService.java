package br.com.avsouza7.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avsouza7.filter.ResultadoFilter;
import br.com.avsouza7.model.Aposta;
import br.com.avsouza7.model.ApostadorDTO;
import br.com.avsouza7.model.Dezena;
import br.com.avsouza7.model.Premio;
import br.com.avsouza7.model.Resultado;
import br.com.avsouza7.model.Sorteio;
import br.com.avsouza7.provider.ApostaProvider;
import br.com.avsouza7.provider.SorteioProvider;
import br.com.avsouza7.repository.ApostadorRepository;
import br.com.avsouza7.util.FormataMonetario;

@Service
public class ResultadoService {

  @Autowired
  private ApostaProvider apostaProvider;
  @Autowired
  private SorteioProvider sorteioProvider;
  @Autowired
  private ApostadorRepository apostadorRepository;
  private BigDecimal valorDoPremio;

  public List<Resultado> getResultados(ResultadoFilter filter) {
    valorDoPremio = BigDecimal.ZERO;
    List<Resultado> resultados = new ArrayList<>();
    Optional<Sorteio> optional = getSorteioDoSite(filter);
    List<Aposta> apostas = apostaProvider.getApostas(filter);
    if (optional.isPresent()) {
      Sorteio sorteio = optional.get();
      apostas.forEach(aposta -> resultados.add(getResultado(filter, aposta, sorteio)));
    }
    return resultados;
  }

  public Optional<Sorteio> getSorteioDoSite(ResultadoFilter filter) {
    return sorteioProvider.getSorteioDoSite(filter);
  }

  private Resultado getResultado(ResultadoFilter filter, Aposta aposta, Sorteio sorteio) {
    Resultado resultado = new Resultado();
    resultado.setDtSorteio(sorteio.getDtSorteio());
    resultado.setIdConcurso(sorteio.getIdConcurso());
    resultado.setIdLoteria(filter.getIdLoteria());
    String[] split = aposta.getDezenasApostadas().split(";");
    for (int i = 0; i < split.length; i++) {
      String string = split[i];
      aposta.getDezenas().add(new Dezena(string));
    }
    aposta.getDezenas().forEach(dezenaAposta -> {
      boolean foiSorteada = sorteio.getDezenas().contains(dezenaAposta);
      dezenaAposta.setFoiSorteada(foiSorteada);
      if (foiSorteada) {
        resultado.setNuAcertos(resultado.getNuAcertos() + 1);
      }
      resultado.getDezenas().add(dezenaAposta);
    });
    BigDecimal vlPremio = getVlPremio(resultado, sorteio);
    valorDoPremio = valorDoPremio.add(vlPremio);
    resultado.setVlPremio(FormataMonetario.brasileiro(vlPremio));
    return resultado;
  }

  private BigDecimal getVlPremio(Resultado resultado, Sorteio sorteio) {
    Map<Integer, BigDecimal> mapaDeFaixas = getMapaDeFaixas(sorteio.getPremios());
    return mapaDeFaixas.get(resultado.getFaixa());
  }

  private Map<Integer, BigDecimal> getMapaDeFaixas(List<Premio> premios) {
    Map<Integer, BigDecimal> map = new HashMap<>();
    map.put(0, BigDecimal.ZERO);
    premios.forEach(premio -> map.put(premio.getFaixa(), premio.getVlPremio()));
    return map;
  }

  public String getValorDoPremio() {
    return FormataMonetario.brasileiro(valorDoPremio);
  }

  public List<ApostadorDTO> getApostadores(ResultadoFilter filter) {
    List<ApostadorDTO> apostadores = new ArrayList<>();
    BigDecimal somaAporte = apostadorRepository.somaAporte(filter.getIdLoteria(),
        filter.getIdGrupo(), filter.getIdConcurso());
    apostadorRepository.findByIdLoteriaAndIdGrupoAndIdConcurso(filter.getIdLoteria(),
        filter.getIdGrupo(), filter.getIdConcurso()).forEach(a -> {
          ApostadorDTO dto = new ApostadorDTO(a.getPessoa());
          BigDecimal valorReceber =
              valorDoPremio.multiply(a.getAporte()).divide(somaAporte, 10, RoundingMode.HALF_UP);
          dto.setAporte(valorReceber);
          apostadores.add(dto);
        });
    return apostadores;
  }

}
