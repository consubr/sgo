package com.canini.sgo.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Cliente;
import com.canini.sgo.services.ClienteService;


@Named
@ViewScoped
public class CadastroClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();
	
	private Long idCliente;
	
	@Inject
	private ClienteService clienteService;
	
	public void inicializar() {
		if (idCliente != null) {
			cliente = clienteService.porId(idCliente);
		}		
	}
	
	public String salvar() {
		clienteService.salvar(cliente);
		return "lista-cliente.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		clienteService.excluir(cliente);
		return "lista-cliente.xhtml?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
		
}
