package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.model.Usuario;
import com.canini.sgo.DAO.PermissaoDAO;
import com.canini.sgo.model.Permissao;
import com.canini.sgo.services.UsuarioService;


@Named
@ViewScoped
public class CadastroUsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	private Long idUsuario;
	
	private List<Permissao> permissoes = new ArrayList<>();
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private PermissaoDAO permissaoDAO;
	
	public void inicializar() {
		if (idUsuario != null) {
			usuario = usuarioService.porId(idUsuario);
		}		
		this.permissoes = permissaoDAO.listarTodas();
		
	}
	
	public String salvar() {
		usuarioService.salvar(usuario);
		return "lista-usuario.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		usuarioService.excluir(usuario);
		return "lista-usuario.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	
	
	
	

}
