package br.com.ragnar.gerenciadortarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ragnar.gerenciadortarefas.model.Usuario;
import br.com.ragnar.gerenciadortarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Usuario retornaUsuarioPeloEmail(String email) {
		return repository.findByEmail(email);
	}

	public void salvaUsuario(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);
	}
}
