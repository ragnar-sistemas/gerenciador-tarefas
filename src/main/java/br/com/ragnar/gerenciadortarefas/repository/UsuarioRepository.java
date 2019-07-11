package br.com.ragnar.gerenciadortarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ragnar.gerenciadortarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}
