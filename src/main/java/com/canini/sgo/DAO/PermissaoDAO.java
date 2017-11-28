package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.Permissao;

public class PermissaoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Permissao> listarTodas() {
		return manager.createNativeQuery("SELECT * FROM permissao", Permissao.class).getResultList();
	}

	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}

}
