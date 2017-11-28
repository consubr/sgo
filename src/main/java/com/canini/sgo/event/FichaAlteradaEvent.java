package com.canini.sgo.event;

import com.canini.sgo.model.FichaTecnica;

public class FichaAlteradaEvent {
	
	private FichaTecnica ficha;
	
	public FichaAlteradaEvent(FichaTecnica ficha) {
		this.ficha = ficha;
	}
	
	public FichaTecnica getFichaTecnica() {
		return ficha;
	}

}
