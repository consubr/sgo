package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Produto;
import com.canini.sgo.services.ProdutoService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class ListaProdutoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoService produtoService;
	
	private List<Produto> produtos = new ArrayList<>();
	
	private List<Produto> produtoPA = new ArrayList<>();
	
	private List<Produto> produtoSelecionados = new ArrayList<>();
	
	private List<Produto> produtoPASelecionados = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		//produtos = produtoService.listAll();
		produtos = produtoService.listAllMP();
		produtoPA = produtoService.listAllPA();
	}
	
	public void excluirSelecionados() {
		try {
			for (Produto produto : produtoSelecionados) {
						produtoService.excluir(produto);
						produtos.remove(produto);
			}
			FacesUtil.addInfoMessage("Materia Prima(s) excluída(s) com sucesso!");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e+"Erro");
		}
	}
	
	public void excluirPASelecionados() {
		try {
			for (Produto produto : produtoPASelecionados) {
						produtoService.excluir(produto);
						produtoPA.remove(produto);
			}
			FacesUtil.addInfoMessage("Produto(s) excluido(s) com sucesso!");
			
						
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao excluir Produto. Mensagem de Erro:"+e);
		}
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public List<Produto> getProdutoPA() {
		return produtoPA;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void setProdutoPA(List<Produto> produtoPA) {
		this.produtoPA = produtoPA;
	}
	
	public List<Produto> getProdutoSelecionados() {
		return produtoSelecionados;
	}
	
	public void setProdutoSelecionados(List<Produto> produtoSelecionados) {
		this.produtoSelecionados = produtoSelecionados;
	}

	public List<Produto> getProdutoPASelecionados() {
		return produtoPASelecionados;
	}

	public void setProdutoPASelecionados(List<Produto> produtoPASelecionados) {
		this.produtoPASelecionados = produtoPASelecionados;
	}
	
	

}
