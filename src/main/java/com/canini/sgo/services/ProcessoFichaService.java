package com.canini.sgo.services;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.canini.sgo.DAO.FichaDAO;
import com.canini.sgo.model.FichaTecnica;
import com.canini.sgo.model.StatusFicha;

public class ProcessoFichaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FichaDAO fichaDAO;
	
	public FichaTecnica processarFicha(FichaTecnica ficha) {
	//	ficha = this.fichaService.salvar(ficha);
		 
//		if (ficha.isNaoProcessa()) {
//			throw new MessageException("Ficha não pode ser processada com status "
//					+ ficha.getFichaStatus().getDescricao() + ".");
//		}
		
		ficha.setFichaStatu(StatusFicha.FINALIZADA);		
		ficha.setFechamento(new Date());
		
		if (ficha.getId() == null) {
			ficha.setCriacao(new Date());
			
		}
		
		ficha = this.fichaDAO.salvar(ficha);
		
		return ficha;
		
	}

}
