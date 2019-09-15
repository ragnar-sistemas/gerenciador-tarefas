package br.com.ragnar.gerenciadortarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ragnar.gerenciadortarefas.model.Aposta;
import br.com.ragnar.gerenciadortarefas.model.Megasena;

@Controller
@RequestMapping("apostas")
public class ApostaController {

	@GetMapping("/megasena")
	public String megasena(Model model) {
		Megasena aposta = new Megasena();
		model.addAttribute("form", aposta);
		return "apostas/megasena";
	}

/*	@GetMapping("/megasena_1")
	public ModelAndView megasena() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aposta", new Megasena());
		modelAndView.setViewName("apostas/megasena");
		return modelAndView;
	}*/

	@PostMapping("/megasena")
	public ModelAndView megasena(@Valid Megasena aposta) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aposta", aposta);
		modelAndView.setViewName("apostas/megasena");
		return modelAndView;
	}

	@GetMapping("/lotofacil")
	public ModelAndView lotofacil() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aposta", new Aposta());
		modelAndView.setViewName("apostas/lotofacil");
		return modelAndView;
	}

	@GetMapping("/quina")
	public ModelAndView quina() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aposta", new Aposta());
		modelAndView.setViewName("apostas/quina");
		return modelAndView;
	}
}
