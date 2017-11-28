package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.TipoProduto;

import com.canini.sgo.services.TipoProdutoService;

@Named
@ViewScoped
public class ListaTPMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProdutoService tpService;	
	
	private List<TipoProduto> tipos = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {		
		tipos = tpService.listAll();
	}

	public List<TipoProduto> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoProduto> tipos) {
		this.tipos = tipos;
	}
	
	

}
