package br.com.avsouza7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.avsouza7.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

