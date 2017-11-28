package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.FichaTecnica;
import com.github.adminfaces.template.exception.BusinessException;

public class FichaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public FichaTecnica salvar(FichaTecnica ficha) {
		return manager.merge(ficha);
	}
	
	public void excluir(FichaTecnica ficha) {
		try {
			ficha = porId(ficha.getId());
			manager.remove(ficha);
			manager.flush();			
		} catch (Exception e) {
			throw new BusinessException("Ficha nao pode ser excluida. Pode existir informações que dependem dessa informação!");
		}
	}
	
	public FichaTecnica porId(Long id) {
		return manager.find(FichaTecnica.class, id);
	}
	
	public List<FichaTecnica> listAll() {
		return manager.createNativeQuery("SELECT * FROM fichatecnica", FichaTecnica.class).getResultList();
		
	}
	
	

}
