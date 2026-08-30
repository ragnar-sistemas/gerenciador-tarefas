package br.com.avsouza7.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.avsouza7.filter.ResultadoFilter;
import br.com.avsouza7.model.Resultado;
import br.com.avsouza7.model.Sorteio;
import br.com.avsouza7.service.ResultadoService;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {

  @Autowired
  private ResultadoService resultadoService;
  // @Autowired
  // private ImagemConcursoUseCase imagemConcursoUseCase;

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
    try {
      if (Objects.isNull(filter.getIdConcurso())) {
        result.rejectValue("idConcurso", "idConcurso.obrigatorio", "Código Concurso é obrigatório");
      }
      if (result.hasErrors()) {
        modelAndView.addObject("resultadoFilter", filter);
      } else {
        List<Resultado> resultados = resultadoService.getResultados(filter);
        modelAndView.addObject("resultados", resultados);
        Optional<Sorteio> sorteioDoSite = resultadoService.getSorteioDoSite(filter);
        if (sorteioDoSite.isPresent()) {
          modelAndView.addObject("sorteios", sorteioDoSite.get());
          modelAndView.addObject("totalDoSeuPremio", "Valor do seu prêmio: ");
          modelAndView.addObject("valorDoPremio", resultadoService.getValorDoPremio());
          modelAndView.addObject("apostadores", resultadoService.getApostadores(filter));
        }
        // imagemConcursoUseCase.imprimir(new ParaMontarImagem()
        // .setIdLoteria(filter.getIdConcurso())
        // .setLoteriaEnum(filter.getLoteriaEnum())
        // .setResultados(resultados)
        // .setSorteio(sorteioDoSite.get())
        // .setValorDoPremio(resultadoService.getValorDoPremio()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      result.rejectValue("idConcurso", "idConcurso.obrigatorio", e.getMessage());
    }
    modelAndView.setViewName("resultados/consultar");
    return modelAndView;
  }

}
