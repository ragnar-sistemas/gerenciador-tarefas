package br.com.avsouza7.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.avsouza7.model.Apostador;

public interface ApostadorRepository extends JpaRepository<Apostador, Long> {

  List<Apostador> findByIdLoteriaAndIdGrupoAndIdConcurso(Long idLoteria, Long idGrupo,
      Long idConcurso);

  @Query("SELECT COALESCE(SUM(a.aporte), 0) FROM Apostador a "
      + "WHERE a.idLoteria = :idLoteria AND a.idGrupo = :idGrupo AND a.idConcurso = :idConcurso")
  BigDecimal somaAporte(@Param("idLoteria") Long idLoteria, @Param("idGrupo") Long idGrupo,
      @Param("idConcurso") Long idConcurso);

  Optional<Apostador> findByPessoa_IdPessoaAndIdLoteriaAndIdGrupoAndIdConcurso(Long idPessoa,
      Long idLoteria, Long idGrupo, Long idConcurso);

}
