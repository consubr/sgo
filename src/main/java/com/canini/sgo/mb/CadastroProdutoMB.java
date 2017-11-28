package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.DAO.TipoProdutoDAO;
import com.canini.sgo.model.Produto;
import com.canini.sgo.model.TipoProduto;
import com.canini.sgo.services.ProdutoService;


@Named
@ViewScoped
public class CadastroProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto = new Produto();
	
	private Long idProduto;
	
	private List<TipoProduto> tiposproduto;
	
	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private TipoProdutoDAO tipoProdutoDAO;
	
	public void inicializar() {
		if (idProduto != null) {
			produto = produtoService.porId(idProduto);
		}
		this.tiposproduto = tipoProdutoDAO.listAllATIVO();
	}

	public String salvar() {
		produtoService.salvar(produto);
		return "lista-produto.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		produtoService.excluir(produto);
		return "lista-produto.xhtml?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public List<TipoProduto> getTipoProduto() {
		return tiposproduto;
	}

	
}
