package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.canini.sgo.DAO.ProdutoDAO;
import com.canini.sgo.annotations.FichaEdicao;
import com.canini.sgo.event.FichaAlteradaEvent;
import com.canini.sgo.model.FichaTecnica;
import com.canini.sgo.model.ItemFicha;
import com.canini.sgo.model.Produto;
import com.canini.sgo.services.FichaService;
import com.canini.sgo.util.FacesUtil;

@Named
@ViewScoped
public class CadastroFichaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@FichaEdicao
	private FichaTecnica ficha = new FichaTecnica();
	
	private Long idFicha;
	
	private Long idProduto;
	
	private List<Produto> produtosficha;
	
	@Inject
	private FichaService fichaService;
	 
	@Inject
	private ProdutoDAO produtoDAO;
	
	private Produto itemLinhaEditavel;
	
	public void inicializar() {		
		if (FacesUtil.isNotPostback()) {			
			if (idFicha != null) {
				ficha = fichaService.porId(idFicha);
			}
			
			this.produtosficha = produtoDAO.listAllPA();
			
			this.ficha.adicionarItemVazio();
			
			this.recalcularFicha();
		}
//		if (ficha.getFechamento() != ficha.getCriacao()){
//			FacesUtil.addWarnMessage("Essa ficha foi processada há muito tempo. Reconsidere processa-lá novamente!");
//		}
	}	
	
	public List<Produto> completarItem(String nome) {
		return this.produtoDAO.porNome(nome);
	}
	
	public void atualizarQuantidade(ItemFicha item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getFicha().getItens().remove(linha);
			}
		}		
		this.ficha.recalcularValorTotal();
	}
	
	public void carregarMateriaPorId() {
		if (this.idProduto != null || this.idProduto.equals("")) {
			
			this.itemLinhaEditavel = this.produtoDAO.porId(this.idProduto);
			this.carregarMateriaLinhaEditavel();
		}
			
	}
	
	public void fichaAlterada(@Observes FichaAlteradaEvent event) {
		this.ficha = event.getFichaTecnica();
	}
	
	public void carregarMateriaLinhaEditavel() {
		ItemFicha item = this.ficha.getItens().get(0);
		
		if (this.itemLinhaEditavel != null) {
			if (this.existeItemnaFicha(this.itemLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item na Ficha com a Matéria-Prima informada!");
			} else {				
			
			item.setProduto(this.itemLinhaEditavel);
			item.setValorUnitario(this.itemLinhaEditavel.getPrecobase());
			
			this.ficha.adicionarItemVazio();
			this.itemLinhaEditavel = null;
			this.idProduto = null;
			
			this.ficha.recalcularValorTotal();
			
			}			
		}
	}

	private boolean existeItemnaFicha(Produto produto) {
		boolean existeItem = false;
		
		for (ItemFicha item : this.getFicha().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public String salvar() {
		this.ficha.removerItemVazio();
		
		try {
			fichaService.salvar(this.ficha);
		} finally {
			this.ficha.adicionarItemVazio();
		}
		
		return "lista-ficha.xhtml?faces-redirect=true";
	}
	
	public String excluir() {
		fichaService.excluir(ficha);
		return "lista-ficha.xhtml?faces-redirect=true";
	}
	
	public void recalcularFicha() {
		if (this.ficha != null) {
			this.ficha.recalcularValorTotal();
		}
	}

	public FichaTecnica getFicha() {
		return ficha;
	}

	public void setFicha(FichaTecnica ficha) {
		this.ficha = ficha;
	}

	public Long getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(Long idFicha) {
		this.idFicha = idFicha;
	}

	public List<Produto> getProdutosficha() {
		return produtosficha;
	}
	
	public Produto getItemLinhaEditavel() {
		return itemLinhaEditavel;
	}

	public void setItemLinhaEditavel(Produto itemLinhaEditavel) {
		this.itemLinhaEditavel = itemLinhaEditavel;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	
	
	
	
	
}
