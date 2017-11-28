package com.canini.sgo.mb;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.annotations.FichaEdicao;
import com.canini.sgo.event.FichaAlteradaEvent;
import com.canini.sgo.model.FichaTecnica;
import com.canini.sgo.services.ProcessoFichaService;
import com.canini.sgo.util.FacesUtil;
import com.canini.sgo.util.Transacional;

@Named
@ViewScoped
public class ProcessoFichaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@FichaEdicao
	private FichaTecnica ficha;

	@Inject
	private ProcessoFichaService processoFichaService;

	@Inject
	private Event<FichaAlteradaEvent> fichaAlteradaEvent;

	@Transacional
	public void processarFicha() {
		this.ficha.removerItemVazio();

		try {
			if (this.ficha.getItens().isEmpty()) {
				FacesUtil.addWarnMessage("A Ficha precia ter pelo menos uma matéria-prima! Favor verificar");
			} else {
				this.ficha = this.processoFichaService.processarFicha(this.ficha);
				this.ficha.getProduto().setPrecofinal(this.ficha.getPreco_custo());
				this.fichaAlteradaEvent.fire(new FichaAlteradaEvent(this.ficha));

				FacesUtil.addInfoMessage("Ficha Técnica processada com sucesso!");
			}
		} finally {
			this.ficha.adicionarItemVazio();
		}

	}
}
