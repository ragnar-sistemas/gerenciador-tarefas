package br.com.avsouza7.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.avsouza7.calculadoradejogos.CalcularJogosService;
import br.com.avsouza7.enuns.LoteriaEnum;
import br.com.avsouza7.filter.Montante;

@Controller
@RequestMapping("/calcular")
public class CalcularApostasController {

	@Autowired
	private CalcularJogosService service;

	@GetMapping("/apostas")
	public ModelAndView consultar() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("montante", new Montante());
		modelAndView.setViewName("calcular/apostas");
		return modelAndView;
	}

	@PostMapping("/apostas")
	public ModelAndView consultar(@Valid Montante filter, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (filter.getMontante() == null) {
				result.rejectValue("montante", "montante.obrigatorio", "Montante é obrigatório");
			}
			if (result.hasErrors()) {
				modelAndView.addObject("montante", filter);
			} else {
				modelAndView.addObject("calculos", service.calcularJogos(LoteriaEnum.getById(filter.getIdLoteria()), filter.getMontante()));
			}
		} catch (Exception e) {
			result.rejectValue("montante", "montante.obrigatorio", e.getMessage());
		}
		modelAndView.setViewName("calcular/apostas");
		return modelAndView;
	}

}
