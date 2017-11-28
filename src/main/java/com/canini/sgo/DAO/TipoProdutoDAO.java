package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.TipoProduto;
import com.github.adminfaces.template.exception.BusinessException;

public class TipoProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public TipoProduto salvar(TipoProduto tp){
		return manager.merge(tp);
	}
	
	public void excluir(TipoProduto tp) {
		try {
			tp = porId(tp.getId());
			manager.remove(tp);
			manager.flush();
		} catch (Exception e) {
			throw new BusinessException("Tipo Produto nao pode ser excluido. Pode existir dependencias de informação. Favor verifique!");
		}
	}

	public TipoProduto porId(Long id) {
		return manager.find(TipoProduto.class, id);
		
	}
	
	public List<TipoProduto> listAll() {
		return manager.createNativeQuery("SELECT * FROM tipoproduto", TipoProduto.class).getResultList();
	}
	
	public List<TipoProduto> listAllATIVO() {
		return manager.createNativeQuery("SELECT * FROM tipoproduto where status = 'ATIVO'", TipoProduto.class).getResultList();
	}
}
