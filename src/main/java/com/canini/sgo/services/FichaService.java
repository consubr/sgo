package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.canini.sgo.DAO.FichaDAO;
import com.canini.sgo.model.FichaTecnica;
import com.canini.sgo.util.Transacional;
import com.github.adminfaces.template.exception.BusinessException;

public class FichaService implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private FichaDAO fichaDAO;
	
	@Transacional
	public FichaTecnica salvar(FichaTecnica ficha) {
		if (ficha.isInclusao()) {
			ficha.setCriacao(new Date());
		}
		
		if (ficha.isEdicao()) {
			ficha.setEdicao(new Date());
		}		
		
		ficha.recalcularValorTotal();
		
		if (ficha.getItens().isEmpty()) {
			throw new BusinessException("O pedido deve possuir pelo menos um item.");
		}
		
		if (ficha.isValorTotalNegativo()) {
			throw new BusinessException("O valor total do pedido não pode ser negativo!");
		}
		
		fichaDAO.salvar(ficha);
		return ficha;
	}
	
	
	@Transacional
	public void excluir(FichaTecnica ficha) {
		fichaDAO.excluir(ficha);
	}
	
	public List<FichaTecnica> listAll() {
		return fichaDAO.listAll();
	}
	
	public FichaTecnica porId(Long id) {
		 FichaTecnica toReturn = fichaDAO.porId(id);
		 return toReturn;
	}
	
	
}
