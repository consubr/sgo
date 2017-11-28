package com.canini.sgo.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;


import com.canini.sgo.DAO.TipoProdutoDAO;
import com.canini.sgo.model.TipoProduto;
import com.canini.sgo.util.Transacional;

public class TipoProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProdutoDAO tpDAO;
	
	@Transacional
	public void salvar(TipoProduto tp) {
		tpDAO.salvar(tp);
	}
	
	@Transacional
	public void excluir(TipoProduto tp) {
		tpDAO.excluir(tp);
	}
	
	public List<TipoProduto> listAll() {
		return tpDAO.listAll();
	}
	
	public List<TipoProduto> listAllATIVO() {
		return tpDAO.listAllATIVO();
	}
	
	public TipoProduto porId(Long id) {
		return tpDAO.porId(id);
	}

}
