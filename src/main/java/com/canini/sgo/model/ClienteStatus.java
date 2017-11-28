package com.canini.sgo.model;

public enum ClienteStatus {
	
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String descricao;
	
	ClienteStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
