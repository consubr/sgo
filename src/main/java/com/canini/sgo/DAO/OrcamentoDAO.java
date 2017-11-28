package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.Orcamento;
import com.github.adminfaces.template.exception.BusinessException;

public class OrcamentoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Orcamento salvar(Orcamento orcamento) {
		return manager.merge(orcamento);
	}
	
	public void excluir(Orcamento orcamento) {
		try  {
			orcamento = porId(orcamento.getId());
			manager.remove(orcamento);
			manager.flush();
		} catch (Exception e) {
			throw new BusinessException("Orcamento nao pode ser excluido. Pode existir informações que dependem dessa informação!");
		}
	}
	
	public Orcamento porId(Long id) {
		return manager.find(Orcamento.class, id);
	}
	
	public List<Orcamento> listAll() {
		return manager.createNativeQuery("SELECT * FROM orcamento", Orcamento.class).getResultList();
	}

}
