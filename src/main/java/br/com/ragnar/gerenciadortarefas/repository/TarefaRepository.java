package br.com.ragnar.gerenciadortarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ragnar.gerenciadortarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :email ")
	List<Tarefa> getTarefas(@Param("email") String email);
}
