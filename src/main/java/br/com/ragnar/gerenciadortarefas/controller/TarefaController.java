package br.com.ragnar.gerenciadortarefas.controller;

import static br.com.ragnar.gerenciadortarefas.util.DateUtil.isMenor;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ragnar.gerenciadortarefas.model.Tarefa;
import br.com.ragnar.gerenciadortarefas.repository.TarefaRepository;
import br.com.ragnar.gerenciadortarefas.service.UsuarioService;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepository repository;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public ModelAndView listar(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tarefas/listar");
		modelAndView.addObject("tarefas", repository.getTarefas(request.getUserPrincipal().getName()));
		return modelAndView;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tarefas/inserir");
		modelAndView.addObject("tarefa", new Tarefa());
		return modelAndView;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		return getModelAndView(tarefa, result, "inserir", request);
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Tarefa tarefa = repository.getOne(id);
		modelAndView.addObject("tarefa", tarefa);
		modelAndView.setViewName("tarefas/alterar");
		return modelAndView;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		return getModelAndView(tarefa, result, "alterar", request);
	}

	private ModelAndView getModelAndView(@Valid Tarefa tarefa, BindingResult result, String action, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "data.nao.pode.ser.nulo", "A data é obrigatória");
		} else if (isMenor(tarefa.getDataExpiracao())) {
			result.rejectValue("dataExpiracao", "data.nao.pode.ser.anterior.a.data.atual", "A data não pode ser anterior a data atual");
		}
		if (result.hasErrors()) {
			modelAndView.setViewName("tarefas/" + action);
			modelAndView.addObject("tarefa", tarefa);
		} else {
			tarefa.setUsuario(usuarioService.retornaUsuarioPeloEmail(request.getUserPrincipal().getName()));
			repository.save(tarefa);
			modelAndView.setViewName("redirect:/tarefas/listar");
		}
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.deleteById(id);
		modelAndView.setViewName("redirect:/tarefas/listar");
		return modelAndView;
	}

	@GetMapping("/concluir/{id}")
	public ModelAndView concluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Tarefa tarefa = repository.getOne(id);
		tarefa.setConcluida(true);
		repository.save(tarefa);
		modelAndView.setViewName("redirect:/tarefas/listar");
		return modelAndView;
	}
}
