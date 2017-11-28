package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.FichaTecnica;
import com.canini.sgo.services.FichaService;

@Named
@ViewScoped
public class ListaFichaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FichaService fichaService;
	
	private List<FichaTecnica> fichas = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		fichas = fichaService.listAll();
	}

	public List<FichaTecnica> getFichas() {
		return fichas;
	}

	public void setFichas(List<FichaTecnica> fichas) {
		this.fichas = fichas;
	}
	
	

}
