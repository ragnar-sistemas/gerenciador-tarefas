package br.com.avsouza7.calculadoradejogos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.avsouza7.enuns.LoteriaEnum;

@RunWith(MockitoJUnitRunner.class)
public class CalcularJogosServiceTest {

	@InjectMocks
	private CalcularJogosService service;

	@Test
	public void nao_nulo() {
		Assert.assertNotNull(service);
	}

	@Test
	public void com_280() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(280));
		Assert.assertEquals("Deveria ter somente uma aposta", 1, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 2\r\nQuantidade dezenas: 8\r\nValor faixa: R$ 140,00", apostas.get(0).toString());
	}

	@Test
	public void com_220() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(220));
		Assert.assertEquals("Deveria ter 3 faixas de apostas", 3, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 2\r\nQuantidade dezenas: 6\r\nValor faixa: R$ 5,00", apostas.get(0).toString());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 2\r\nQuantidade dezenas: 7\r\nValor faixa: R$ 35,00", apostas.get(1).toString());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 1\r\nQuantidade dezenas: 8\r\nValor faixa: R$ 140,00", apostas.get(2).toString());
	}

	@Test
	public void com_21() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(21));
		Assert.assertEquals("Deveria ter 1 faixa de apostas", 1, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 4\r\nQuantidade dezenas: 6\r\nValor faixa: R$ 5,00", apostas.get(0).toString());
	}

	@Test
	public void com_4() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(4));
		Assert.assertEquals("Deveria ter 0 faixa de apostas", 0, apostas.size());
	}

	@Test
	public void com_25() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(25));
		Assert.assertEquals("Deveria ter 1 faixa de apostas", 1, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 5\r\nQuantidade dezenas: 6\r\nValor faixa: R$ 5,00", apostas.get(0).toString());
	}

	@Test
	public void com_13() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(13.9));
		Assert.assertEquals("Deveria ter 1 faixa de apostas", 1, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 2\r\nQuantidade dezenas: 6\r\nValor faixa: R$ 5,00", apostas.get(0).toString());
	}

	@Test
	public void com_100() {
		List<CalcularJogosService.ApostasSugeridas> apostas = service.calcularJogos(LoteriaEnum.MEGASENA, BigDecimal.valueOf(100));
		Assert.assertEquals("Deveria ter 2 faixa de apostas", 2, apostas.size());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 6\r\nQuantidade dezenas: 6\r\nValor faixa: R$ 5,00", apostas.get(0).toString());
		Assert.assertEquals("Loteria: Mega-Sena\r\nQuantidae apostas: 2\r\nQuantidade dezenas: 7\r\nValor faixa: R$ 35,00", apostas.get(1).toString());
	}

}
