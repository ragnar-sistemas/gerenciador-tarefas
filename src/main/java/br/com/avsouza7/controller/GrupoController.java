package br.com.avsouza7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.avsouza7.model.Grupo;
import br.com.avsouza7.repository.GrupoRepository;

@Controller
@RequestMapping("/grupos")
public class GrupoController {

  @Autowired
  private GrupoRepository repository;

  @GetMapping("/listar")
  public ModelAndView listar() {
    ModelAndView mv = new ModelAndView("grupos/listar");
    mv.addObject("grupos", repository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    return mv;
  }

  @GetMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView("grupos/cadastro");
    mv.addObject("grupo", new Grupo());
    return mv;
  }

  @GetMapping("/{id}/editar")
  public ModelAndView editar(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView("grupos/cadastro");
    mv.addObject("grupo", repository.findById(id).orElseThrow());
    return mv;
  }

  @PostMapping("/salvar")
  public String salvar(@ModelAttribute Grupo grupo) {
    repository.save(grupo);
    return "redirect:/grupos/listar";
  }

  @GetMapping("/{id}/excluir")
  public String excluir(@PathVariable Long id) {
    repository.deleteById(id);
    return "redirect:/grupos/listar";
  }
}


