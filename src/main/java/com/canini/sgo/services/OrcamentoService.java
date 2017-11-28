package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.canini.sgo.DAO.OrcamentoDAO;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.util.Transacional;
import com.github.adminfaces.template.exception.BusinessException;

public class OrcamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrcamentoDAO orcamentoDAO;
	
	@Transacional
	public Orcamento salvar(Orcamento orcamento) {
		if (orcamento.isInclusao()) {
			orcamento.setDataCriacao(new Date());			
		}
		if (orcamento.isEdicao()) {
			orcamento.setDataEdicao(new Date());	
		}
		
		orcamento.recalcularValorTotal();
		
		if (orcamento.getItens().isEmpty()) {
			throw new BusinessException("O Orcamento deve possuir pelo menos um item.");
		}
		
		if (orcamento.isValorTotalNegativo()) {
			throw new BusinessException("O valor total do Orcamento não pode ser negativo!");
		}
		
		orcamentoDAO.salvar(orcamento);
		
		return orcamento;
	}
	
	@Transacional
	public void excluir(Orcamento orcamento) {
		orcamentoDAO.excluir(orcamento);
	}
	
	public List<Orcamento> listAll() {
		return orcamentoDAO.listAll();
	}
	
	public Orcamento porId(Long id) {
		return orcamentoDAO.porId(id);
	}

}
