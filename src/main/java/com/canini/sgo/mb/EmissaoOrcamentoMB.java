package com.canini.sgo.mb;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.annotations.OrcamentoEdicao;
import com.canini.sgo.event.OrcamentoAlteradoEvent;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.services.EmissaoOrcamentoService;
import com.canini.sgo.util.FacesUtil;
import com.canini.sgo.util.Transacional;

@Named
@ViewScoped
public class EmissaoOrcamentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@OrcamentoEdicao
	private Orcamento orcamento;

	@Inject
	private EmissaoOrcamentoService emissaoOrcamentoService;

	@Inject
	private Event<OrcamentoAlteradoEvent> orcamentoAlteradoEvent;

	@Transacional
	public void emitirOrcamento() {
		this.orcamento.removerItemVazio();

		try {
			if (this.orcamento.getItens().isEmpty()) {
				FacesUtil.addWarnMessage("O orçamento precisa ter pelo menos um item! Favor verificar");
			} else {
				this.orcamento = this.emissaoOrcamentoService.emitirOrcamento(this.orcamento);
				this.orcamentoAlteradoEvent.fire(new OrcamentoAlteradoEvent(this.orcamento));

				FacesUtil.addInfoMessage("Orcamento emitido com sucesso!");
			}
		} finally {
			this.orcamento.adicionarItemVazio();
		}

	}

}
