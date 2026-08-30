package br.com.avsouza7.sortearamigosecreto;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SortearServiceTest {

	@InjectMocks
	private SortearService service;

	@Test
	public void nao_nulo() {
		Assert.assertNotNull(service);
	}

	@Test
	public void sortear_pares() {
		service.sortear(amigos());
		System.out.println(service.getSorteio());
	}

	private List<Amigo> amigos() {
		return List.of(new Amigo("Alexandre", "alexandre@email.com"), new Amigo("Isabela", "isabela@email.com"), new Amigo("Josi", "josi@email.com"));
	}

}
