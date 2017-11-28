package com.canini.sgo.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.TipoProduto;
import com.canini.sgo.services.TipoProdutoService;

@Named
@ViewScoped
public class CadastroTPMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TipoProduto tipoproduto = new TipoProduto();
	
	private Long idTP;
	
	@Inject
	private TipoProdutoService tpService;
	
	public void inicializar() {
		if (idTP != null) {
			tipoproduto = tpService.porId(idTP);
		}
	}
	
	public String salvar() {
		tpService.salvar(tipoproduto);
		return "lista-tipo.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		tpService.excluir(tipoproduto);
		return "lista-tipo.xhtml?faces-redirect=true";
	}

	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}

	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}

	public Long getIdTP() {
		return idTP;
	}

	public void setIdTP(Long idTP) {
		this.idTP = idTP;
	}
	
	
	

}
