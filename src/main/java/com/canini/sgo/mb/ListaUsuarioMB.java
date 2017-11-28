package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Usuario;
import com.canini.sgo.services.UsuarioService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class ListaUsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	private List<Usuario> usuarioSelecionados = new ArrayList<>();
	
	@PostConstruct
	public void inicializar() {
		usuarios = usuarioService.listAll();
	}
	
	public void excluirSelecionados() {
		try {
			for (Usuario usuario : usuarioSelecionados) {
				usuarioService.excluir(usuario);
				usuarios.remove(usuario);
			}
			FacesUtil.addInfoMessage("Usuario(s) excluido(s) com sucesso.");
			
	} catch (Exception e) {
		FacesUtil.addErrorMessage(e+"Erro");
	 }
    }
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public List<Usuario> getUsuarioSelecionados() {
		return usuarioSelecionados;
	}
	
	public void setUsuarioSelecionados(List<Usuario> usuarioSelecionados) {
		this.usuarioSelecionados = usuarioSelecionados;
	}
	
	
	
}
