package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.canini.sgo.DAO.PermissaoDAO;
import com.canini.sgo.DAO.UsuarioDAO;
import com.canini.sgo.model.Usuario;
import com.canini.sgo.util.Transacional;

public class UsuarioService implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transacional
	public void salvar(Usuario usuario) {
		
		if (usuario.isInclusao()) {
			usuario.setCriacao(new Date());
		}
		
		if (usuario.isEdicao()) {
			usuario.setEdicao(new Date());
		}
		
		usuarioDAO.salvar(usuario);
	}
	
	
	@Transacional
	public void excluir(Usuario usuario) {
		usuarioDAO.excluir(usuario);
	}
	
	public List<Usuario> listAll() {
		return usuarioDAO.listAll();
	}
	
	public List<Usuario> listAllATIVO() {
		return usuarioDAO.listAllATIVO();
	}
	
	public Usuario porId(Long id) {
		return usuarioDAO.porId(id);
	}

}
