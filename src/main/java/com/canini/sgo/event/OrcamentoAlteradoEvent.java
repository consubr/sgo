package com.canini.sgo.event;

import com.canini.sgo.model.Orcamento;

public class OrcamentoAlteradoEvent {
	
	
	private Orcamento orcamento;
	
	public OrcamentoAlteradoEvent(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	public Orcamento getOrcamento() {
		return orcamento;
	}

}
