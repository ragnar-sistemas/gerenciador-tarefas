package br.com.avsouza7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.avsouza7.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :email ")
	List<Tarefa> getTarefas(@Param("email") String email);
}
