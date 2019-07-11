package br.com.ragnar.gerenciadortarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ragnar.gerenciadortarefas.model.Usuario;
import br.com.ragnar.gerenciadortarefas.service.UsuarioService;

@Controller
public class ContaController {

	@Autowired
	private UsuarioService service;

	@GetMapping("/login")
	public String login() {
		return "conta/login";
	}

	@GetMapping("/registration")
	public ModelAndView registrar() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("conta/registrar");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

	@PostMapping("/registration")
	public ModelAndView registrar(@Valid Usuario usuario, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuarioBanco = service.retornaUsuarioPeloEmail(usuario.getEmail());
		if (usuarioBanco != null) {
			result.rejectValue("email", "", "Usuário já cadastrado");
		}
		if (usuario.getSenhaConfirmar() == null || usuario.getSenhaConfirmar().isEmpty()) {
			result.rejectValue("senhaConfirmar", "", "Senha de confirmação não informada");
		} else if (!usuario.getSenha().contentEquals(usuario.getSenhaConfirmar())) {
			result.rejectValue("senhaConfirmar", "", "Senhas não correspondem");
		}
		if (result.hasErrors()) {
			modelAndView.setViewName("conta/registrar");
			modelAndView.addObject("usuario", usuario);
		} else {
			service.salvaUsuario(usuario);
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
}
