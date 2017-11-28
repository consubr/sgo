package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Cliente;
import com.canini.sgo.services.ClienteService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class ListaClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteService clienteService;
	
	private List<Cliente> clientes = new ArrayList<>();
	
	private List<Cliente> clienteSelecionados = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		clientes = clienteService.listAll();
	}
	
	public void excluirSelecionados() {
		try {
			for (Cliente cliente : clienteSelecionados) {
						clienteService.excluir(cliente);
						clientes.remove(cliente);
					}		
					FacesUtil.addInfoMessage("Cliente(s) excluido(s) com sucesso!");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e+"Erro");
		}	

	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Cliente> getClienteSelecionados() {
		return clienteSelecionados;
	}
	
	public void setClienteSelecionados(List<Cliente> clienteSelecionados) {
		this.clienteSelecionados = clienteSelecionados;
	}
	
	

}
