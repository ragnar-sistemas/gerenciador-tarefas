package br.com.ragnar.gerenciadortarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ragnar.gerenciadortarefas.filter.ResultadoFilter;
import br.com.ragnar.gerenciadortarefas.service.ResultadoService;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@GetMapping("/consultar")
	public ModelAndView consultar() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultadoFilter", new ResultadoFilter());
		modelAndView.setViewName("resultados/consultar");
		return modelAndView;
	}

	@PostMapping("/consultar")
	public ModelAndView consultar(@Valid ResultadoFilter filter, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (filter.getIdConcurso() == null) {
			result.rejectValue("idConcurso", "idConcurso.obrigatorio", "Concurso é obrigatório");
		}
		if (filter.getIdLoteria() == null) {
			result.rejectValue("idLoteria", "idLoteria.obrigatorio", "Loteria é obrigatório");
		}
		if (result.hasErrors()) {
			modelAndView.addObject("resultadoFilter", filter);
		} else {
			modelAndView.addObject("resultados", resultadoService.getResultados(filter));
		}
		modelAndView.setViewName("resultados/consultar");
		return modelAndView;
	}

}
