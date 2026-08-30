package br.com.avsouza7.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.avsouza7.filter.ResultadoFilter;
import br.com.avsouza7.model.Aposta;
import br.com.avsouza7.model.ApostadorDTO;
import br.com.avsouza7.model.CadastroAposta;
import br.com.avsouza7.model.Dezena;
import br.com.avsouza7.provider.ApostaProvider;
import br.com.avsouza7.repository.ApostaRepository;
import br.com.avsouza7.repository.ApostadorRepository;
import br.com.avsouza7.repository.PessoaRepository;
import br.com.avsouza7.service.CadastroApostaService;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    private ApostaRepository apostaRepository;
    @Autowired
    private CadastroApostaService cadastroApostaService;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ApostadorRepository apostadorRepository;
    @Autowired
    private ApostaProvider apostaProvider;

    @GetMapping("/listar")
    public ModelAndView listar() {
	ModelAndView mv = new ModelAndView("apostas/listar");
	mv.addObject("apostas", apostaRepository.findAll(Sort.by(Sort.Direction.DESC, "dtSorteio")));
	return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
	ModelAndView mv = new ModelAndView("apostas/cadastro");
	CadastroAposta cadastro = new CadastroAposta();
	cadastro.getDezenas().add("");
	pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")).stream()
		.filter(p -> "S".equals(p.getFlAtivo()))
		.forEach(p -> cadastro.getApostadores().add(new ApostadorDTO(p)));
	mv.addObject("aposta", cadastro);
	return mv;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
	ModelAndView mv = new ModelAndView("apostas/cadastro");
	mv.addObject("aposta", cadastroApostaService.findById(id).orElseThrow());
	return mv;
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute CadastroAposta aposta, BindingResult result) {
	if (result.hasErrors()) {
	    return "apostas/cadastro";
	}
	cadastroApostaService.save(aposta);
	return "redirect:/apostas/listar";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
	apostaRepository.deleteById(id);
	return "redirect:/apostas/listar";
    }

    @GetMapping("/apostas")
    public ModelAndView consultar() {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("resultadoFilter", new ResultadoFilter());
	modelAndView.setViewName("apostas/apostas");
	return modelAndView;
    }

    @PostMapping("/apostas")
    public ModelAndView apostas(@Valid ResultadoFilter filter, BindingResult result) {
	ModelAndView mv = new ModelAndView("apostas/apostas");
	List<Aposta> apostas = apostaProvider.getApostas(filter);
	apostas.forEach(aposta -> {
	    String[] split = aposta.getDezenasApostadas().split(";");
	    for (int i = 0; i < split.length; i++) {
		String string = split[i];
		aposta.getDezenas().add(new Dezena(string));
	    }
	});
	mv.addObject("apostas", apostas);
	mv.addObject("apostadores", apostadorRepository.findByIdLoteriaAndIdGrupoAndIdConcurso(filter.getIdLoteria(),
		filter.getIdGrupo(), filter.getIdConcurso()));
	return mv;
    }

}
