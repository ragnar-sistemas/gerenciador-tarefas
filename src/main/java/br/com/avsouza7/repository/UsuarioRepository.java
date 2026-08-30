package br.com.avsouza7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avsouza7.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}
