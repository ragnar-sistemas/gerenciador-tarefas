package br.com.avsouza7.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.avsouza7.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

  List<Aposta> findByIdLoteriaAndIdGrupoAndNuConcurso(Long idLoteria, Long idGrupo,
      Long nuConcurso);


}
