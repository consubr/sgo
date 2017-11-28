package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.canini.sgo.model.Usuario;
import com.github.adminfaces.template.exception.BusinessException;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario salvar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public void excluir(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
			
		} catch (Exception e) {
			throw new BusinessException("Usuario nao pode ser excluido. Pode existir dependencias de informação. Favor verifique!");
		}
	}
	
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public List<Usuario> listAll() {
		return manager.createNativeQuery("SELECT * FROM usuario", Usuario.class).getResultList();
		
	}
	
	public List<Usuario> listAllATIVO() {
		return manager.createNativeQuery("SELECT * FROM usuario where status = 'ATIVO' and username NOT Like '%admin%'", Usuario.class).getResultList();
		
	}

	public Usuario porUsername(String username) {
		Usuario usuario = null;
		
		try {
		usuario = this.manager.createQuery("from Usuario where lower(username) = :username and status = 'ATIVO'", Usuario.class)
				.setParameter("username", username.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessException("Não foi encontrado usuario com esse nome");
		}
		return usuario;
	}
	

}
