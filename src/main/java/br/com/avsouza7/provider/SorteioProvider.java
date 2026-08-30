package br.com.avsouza7.provider;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.avsouza7.exceptions.LeituraException;
import br.com.avsouza7.filter.ResultadoFilter;
import br.com.avsouza7.model.Sorteio;

@Service
public class SorteioProvider {
  private static final String NAO_FOI_POSSIVEL_CONSULTAR =
      "Não foi possível consultar pelo código do concurso.";
  private static final String OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO =
      "Ops! Alguma coisa não saiu como esperado.";
  private static final String URL = "https://servicebus2.caixa.gov.br/portaldeloterias/api/%s/%s";
  private static Map<String, Sorteio> cache = new HashMap<>();

  public Optional<Sorteio> getSorteioDoSite(ResultadoFilter filter) {
    try {
      Optional<Sorteio> optional = Optional.ofNullable(cache.get(keyCache(filter)));
      if (optional.isPresent()) {
        return optional;
      }
      URI uri = new URI(String.format(URL, filter.getLoteriaEnum().name(), filter.getIdConcurso()));
      Optional<Sorteio> sorteio =
          Optional.ofNullable(new RestTemplate().getForObject(uri, Sorteio.class));
      sorteio.ifPresentOrElse(s -> {
        s.setIdLoteria(filter.getIdLoteria());
        s.setIdConcurso(filter.getIdConcurso());
        cache.put(keyCache(filter), s);
      }, null);
      return sorteio;
    } catch (HttpServerErrorException e) {
      throw new LeituraException(NAO_FOI_POSSIVEL_CONSULTAR, e);
    } catch (Exception e) {
      throw new LeituraException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO, e);
    }
  }

  private String keyCache(ResultadoFilter filter) {
    return filter.getLoteriaEnum().name() + "_" + filter.getIdConcurso();
  }

}
