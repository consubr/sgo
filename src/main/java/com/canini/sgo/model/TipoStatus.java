package com.canini.sgo.model;

public enum TipoStatus {
	
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String descricao;
	
	TipoStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
