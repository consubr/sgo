package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.canini.sgo.DAO.OrcamentoDAO;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.model.StatusOrcamento;

public class EmissaoOrcamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrcamentoDAO orcamentoDAO;

	
	public Orcamento emitirOrcamento(Orcamento orcamento) {

	 	orcamento.setStatusorcamento(StatusOrcamento.EMITIDO);
		orcamento.setDataFinalizacao(new Date());

		if (orcamento.getId() == null) {
			orcamento.setDataCriacao(new Date());

		}

		orcamento = this.orcamentoDAO.salvar(orcamento);

		return orcamento;

	}

}
