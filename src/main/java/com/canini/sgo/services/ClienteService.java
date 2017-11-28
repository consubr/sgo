package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.canini.sgo.DAO.ClienteDAO;
import com.canini.sgo.model.Cliente;
import com.canini.sgo.util.Transacional;

public class ClienteService implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Transacional
	public void salvar(Cliente cliente) {
		if (cliente.isInclusao()) {
			cliente.setCriacao(new Date());
		}
		
		if (cliente.isEdicao()) {
			cliente.setEdicao(new Date());
		}
		
		clienteDAO.salvar(cliente);
	}
	
	
	@Transacional
	public void excluir(Cliente cliente) {
		clienteDAO.excluir(cliente);
	}
	
	public List<Cliente> listAll() {
		return clienteDAO.listAll();
	}
	
	public Cliente porId(Long id) {
		return clienteDAO.porId(id);
	}

}
