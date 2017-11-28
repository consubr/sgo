package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Orcamento;
import com.canini.sgo.services.OrcamentoService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class ListaOrcamentoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrcamentoService orcamentoService;
	
	private List<Orcamento> orcamentos = new ArrayList<>();
	
	private List<Orcamento> orcamentoSelecionados = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		orcamentos = orcamentoService.listAll();
	}
	
	public void excluirSelecionados() {
		try {
			for (Orcamento orcamento : orcamentoSelecionados) {
				orcamentoService.excluir(orcamento);
				orcamentos.remove(orcamento);
			}
			FacesUtil.addInfoMessage("Orcamento(s) excluido(s) com sucesso!");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e+"Erro");
		}
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public List<Orcamento> getOrcamentoSelecionados() {
		return orcamentoSelecionados;
	}

	public void setOrcamentoSelecionados(List<Orcamento> orcamentoSelecionados) {
		this.orcamentoSelecionados = orcamentoSelecionados;
	}
	
}
