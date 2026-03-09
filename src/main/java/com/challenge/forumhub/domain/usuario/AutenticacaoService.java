package com.challenge.forumhub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
@Autowired
private UsuarioRepository usuarioRepository;

@Override
public UserDetails loadUserByUsername(String nome) {
    var usuario = usuarioRepository.findByLogin(nome);

    if (usuario == null) {
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    return usuario;
}
}
