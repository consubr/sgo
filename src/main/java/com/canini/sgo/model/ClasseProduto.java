package com.canini.sgo.model;

public enum ClasseProduto {
	
	PA("Produto Acabado"),
	MP("Materia Prima");
	
	private String descricao;
	
	ClasseProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
