package com.canini.sgo.model;

public enum StatusOrcamento {
	
	ANDAMENTO("Em Andamento"), 
	EMITIDO("Emitido"), 
	APROVADO("Aprovado");
	
	private String descricao;
	
	StatusOrcamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
