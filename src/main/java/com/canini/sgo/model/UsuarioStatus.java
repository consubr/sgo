package com.canini.sgo.model;

public enum UsuarioStatus {
	
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String descricao;
	
	UsuarioStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
