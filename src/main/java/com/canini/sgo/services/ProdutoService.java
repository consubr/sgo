package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.canini.sgo.DAO.ProdutoDAO;
import com.canini.sgo.model.Produto;
import com.canini.sgo.util.Transacional;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Transacional
	public void salvar(Produto produto) {
		if (produto.isInclusao()) {
			produto.setCriacao(new Date());
		}
		
		if (produto.isEdicao()) {
			produto.setEdicao(new Date());
		}
		
		produtoDAO.salvar(produto);
		
	}
	
	@Transacional
	public void excluir(Produto produto) {
		produtoDAO.excluir(produto);
	}
	
	public List<Produto> listAll() {
		return produtoDAO.listAll();
	}
	
	public List<Produto> listAllMP() {
		return produtoDAO.listAllMP();
	}
	
	public List<Produto> listAllPA() {
		return produtoDAO.listAllPA();
	}
	
	public Produto porId(Long id) {
		return produtoDAO.porId(id);
	}

}
