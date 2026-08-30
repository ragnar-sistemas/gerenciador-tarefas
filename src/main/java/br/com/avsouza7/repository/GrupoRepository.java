package br.com.avsouza7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.avsouza7.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}

