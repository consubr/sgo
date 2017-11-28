package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.DAO.ClienteDAO;
import com.canini.sgo.DAO.ProdutoDAO;
import com.canini.sgo.DAO.UsuarioDAO;
import com.canini.sgo.annotations.OrcamentoEdicao;
import com.canini.sgo.event.OrcamentoAlteradoEvent;
import com.canini.sgo.model.Cliente;
import com.canini.sgo.model.ItemOrcamento;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.model.Produto;
import com.canini.sgo.model.StatusOrcamento;
import com.canini.sgo.model.Usuario;
import com.canini.sgo.services.OrcamentoService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class CadastroOrcamentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@OrcamentoEdicao
	private Orcamento orcamento = new Orcamento();

	private List<Cliente> clienteOrcamento;

	private List<Usuario> usuarioOrcamento;

	private Long idOrcamento;

	@Inject
	private OrcamentoService orcamentoService;

	@Inject
	private ClienteDAO clienteDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private ProdutoDAO produtoDAO;

	private Produto produtoLinhaEditavel;

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			if (idOrcamento != null) {
				orcamento = orcamentoService.porId(idOrcamento);
			}

			this.orcamento.adicionarItemVazio();
			this.recalcularOrcamento();
		}
		this.clienteOrcamento = clienteDAO.listAllATIVO();
		this.usuarioOrcamento = usuarioDAO.listAllATIVO();
	}

	public String salvar() {
		this.orcamento.removerItemVazio();

		try {
			orcamentoService.salvar(this.orcamento);
		} finally {
			this.orcamento.adicionarItemVazio();
		}

		return "lista-orcamento.xhtml?faces-redirect=true";
	}

	public void recalcularOrcamento() {
		this.orcamento.recalcularValorTotal();
	}

	public void orcamentoAlterado(@Observes OrcamentoAlteradoEvent event) {
		this.orcamento = event.getOrcamento();
	}

	public String excluir() {
		orcamentoService.excluir(orcamento);
		return "lista-orcamento.xhtml?faces-redirect=true";
	}

	public String aprovar() {
		this.orcamento.setStatusorcamento(StatusOrcamento.APROVADO);
		this.orcamento.setDataEdicao(new Date());
		this.orcamentoService.salvar(orcamento);
		return "lista-orcamento.xhtml?faces-redirect=true";
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoDAO.porProduto(nome);
	}

	public void atualizarQuantidade(ItemOrcamento item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getOrcamento().getItens().remove(linha);
			}
		}
		this.orcamento.recalcularValorTotal();
	}

	public void carregarProdutoLinhaEditavel() {

		ItemOrcamento item = this.orcamento.getItens().get(0);
		if (this.produtoLinhaEditavel != null) {

			if (this.existeItem(this.produtoLinhaEditavel)) {
				FacesUtil.addWarnMessage("Já existe um item na Ficha com a Matéria-Prima informada!");
				this.produtoLinhaEditavel = null;
			} else

			if (this.semPreco(this.produtoLinhaEditavel)) {
				FacesUtil.addWarnMessage("Produto com valor total sem branco, favor verificar ficha técnica!");
				this.produtoLinhaEditavel = null;
			} else {

				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getPrecofinal());

				this.orcamento.adicionarItemVazio();
				this.produtoLinhaEditavel = null;

				this.orcamento.recalcularValorTotal();
			}

		}

	}

	private boolean existeItem(Produto produto) {
		boolean existeItem = false;

		for (ItemOrcamento item : this.getOrcamento().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		return existeItem;
	}

	private boolean semPreco(Produto produto) {
		boolean semPreco = false;

		
			if (produto.getPrecofinal() == null) {
				semPreco = true;
			}
		
		return semPreco;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Long getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(Long idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public List<Cliente> getClienteOrcamento() {
		return clienteOrcamento;
	}

	public List<Usuario> getUsuarioOrcamento() {
		return usuarioOrcamento;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

}
