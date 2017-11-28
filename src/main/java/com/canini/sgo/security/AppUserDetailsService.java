package com.canini.sgo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.canini.sgo.DAO.UsuarioDAO;
import com.canini.sgo.model.Permissao;
import com.canini.sgo.model.Usuario;
import com.canini.sgo.util.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioDAO usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarios.porUsername(username);
		
		UsuarioSistema user = null;
		
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getPermissoes(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Permissao permissao : usuario.getPermissoes()) {
			authorities.add(new SimpleGrantedAuthority(permissao.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}