package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.Cliente;
import com.github.adminfaces.template.exception.BusinessException;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Cliente salvar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	public void excluir(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();			
		} catch (Exception e) {
			throw new BusinessException("Cliente nao pode ser excluido. Pode existir informações que dependem dessa informação!");
		}
	}
	
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	public List<Cliente> listAll() {
		return manager.createNativeQuery("SELECT * FROM cliente", Cliente.class).getResultList();
		
	}
	
	public List<Cliente> listAllATIVO() {
		return manager.createNativeQuery("SELECT * FROM cliente where status = 'ATIVO'", Cliente.class).getResultList();
		
	}
	
	

}
