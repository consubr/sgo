package com.canini.sgo.model;

public enum StatusFicha {
	
	ANDAMENTO("Em Andamento"),
	FINALIZADA("Finalizado");
	
	private String descricao;
	
	StatusFicha(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
