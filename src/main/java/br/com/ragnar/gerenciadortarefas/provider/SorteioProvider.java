package br.com.ragnar.gerenciadortarefas.provider;

import static java.lang.Long.valueOf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.ragnar.gerenciadortarefas.filter.ResultadoFilter;
import br.com.ragnar.gerenciadortarefas.model.Dezena;
import br.com.ragnar.gerenciadortarefas.model.Premio;
import br.com.ragnar.gerenciadortarefas.model.Sorteio;
import br.com.ragnar.gerenciadortarefas.util.DateUtil;

public class SorteioProvider {
	private static final String OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO = "Ops! Alguma coisa não saiu como esperado.";
	private static final String LINK = "https://www.intersena.com.br/resultados/%s/%s";
	private static final String VAZIO = "";
	private static final String REGEX = "\\<.*?>";

	public Optional<Sorteio> getSorteioDoSite(ResultadoFilter filter) {
		String link = String.format(LINK, filter.getDados().getUrl(), filter.getIdConcurso());
		Document doc;
		Sorteio sorteio = new Sorteio();
		try {
			doc = Jsoup.connect(link).get();
			Element form = doc.select("form").get(0);
			Elements spans = form.select("span");
			Elements ps = form.select("p");
			Elements b = ps.select("b");
			sorteio.setPremios(getPremios(form));
			sorteio.setIdConcurso(filter.getIdConcurso());
			sorteio.setDtSorteio(DateUtil.toDate(b.get(0).toString().replaceAll(REGEX, VAZIO).trim()));
			for (int i = filter.getDados().getInicio(); i <= filter.getDados().getFim(); i++) {
				sorteio.getDezenas().add(getDezena(spans.get(i)));
			}
		} catch (IOException e) {
			throw new IllegalArgumentException(OPS_ALGUMA_COISA_NAO_SAIU_COMO_ESPERADO, e);
		}
		return Optional.of(sorteio);
	}

	private List<Premio> getPremios(Element form) {
		List<Premio> premios = new ArrayList<>();
		Elements div = form.select("div#ctl00_cphMainContent_divRateioDoPremio");
		Elements tbody = div.select("tbody");
		for (Element element : tbody) {
			Elements trs = element.select("tr");
			for (Element tr : trs) {
				premios.add(getPremio(tr));
			}
		}
		return premios;
	}

	private Premio getPremio(Element tr) {
		Premio premio = new Premio();
		Elements td = tr.select("td");
		premio.setFaixa(td.get(0).toString().replaceAll(REGEX, VAZIO).trim());
		premio.setGanhadores(td.get(1).toString().replaceAll(REGEX, VAZIO).trim());
		premio.setPremio(td.get(2).toString().replaceAll(REGEX, VAZIO).trim());
		return premio;
	}

	private Dezena getDezena(Element element) {
		Dezena dezena = new Dezena();
		dezena.setIdDezena(valueOf(element.toString().replaceAll(REGEX, VAZIO).trim()));
		return dezena;
	}
}
