package com.canini.sgo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "fichatecnica")
public class FichaTecnica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date criacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_edicao")
	private Date edicao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fechamento")
	private Date fechamento;

	@Enumerated(EnumType.STRING)
	@Column
	private StatusFicha fichaStatus = StatusFicha.ANDAMENTO;

	@OneToOne
	private Produto produto;

	@Column
	private String altura;

	@Column
	private String largura;

	@Column
	private String comprimento;

	@Column(name = "valor_maodeobra")
	private BigDecimal maodeobra = BigDecimal.ZERO;

	@Column(name = "valor_precocusto")
	private BigDecimal preco_custo = BigDecimal.ZERO;

	@OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemFicha> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getEdicao() {
		return edicao;
	}

	public void setEdicao(Date edicao) {
		this.edicao = edicao;
	}

	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public String getComprimento() {
		return comprimento;
	}

	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getMaodeobra() {
		return maodeobra;
	}

	public void setMaodeobra(BigDecimal maodeobra) {
		this.maodeobra = maodeobra;
	}

	public BigDecimal getPreco_custo() {
		return preco_custo;
	}

	public void setPreco_custo(BigDecimal preco_custo) {
		this.preco_custo = preco_custo;
	}

	public StatusFicha getFichaStatus() {
		return fichaStatus;
	}

	public void setFichaStatu(StatusFicha fichaStatus) {
		this.fichaStatus = fichaStatus;
	}

	public List<ItemFicha> getItens() {
		return itens;
	}

	public void setItens(List<ItemFicha> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichaTecnica other = (FichaTecnica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public BigDecimal getValorSubtotal() {
		return this.getPreco_custo().subtract(this.getMaodeobra());
	}

	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(this.getMaodeobra());

		for (ItemFicha item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorTotal());
			}
		}

		this.setPreco_custo(total);
	}

	public void adicionarItemVazio() {
		if (this.isAndamento()) {

			ItemFicha item = new ItemFicha();
			item.setProduto(new Produto());
			item.setFicha(this);
			this.getItens().add(0, item);
		}

	}

	@Transient
	public boolean isAndamento() {
		return StatusFicha.ANDAMENTO.equals(this.getFichaStatus());
	}

	public void removerItemVazio() {
		ItemFicha primeiroItem = this.getItens().get(0);
		
		if (primeiroItem != null && primeiroItem.getProduto() != null && primeiroItem.getProduto().getId() == null) {
			this.getItens().remove(0);
		}
		
	}
	
	@Transient
	public boolean isValorTotalNegativo() {
		return this.getPreco_custo().compareTo(BigDecimal.ZERO) < 0;
	}
	
	@Transient
	public boolean isNaoProcessa() {
		return !this.isProcessa();
	}
	
	@Transient
	public boolean isProcessa() {
		return this.isExistente() && this.isAndamento();
	}
	
	@Transient
	public boolean isInclusao() {
		return getId() == null ? true : false;
	}

	@Transient
	public boolean isEdicao() {
		return !isInclusao();
	}

	@Transient
	public boolean isFinalizado() {
		return StatusFicha.FINALIZADA.equals(this.getFichaStatus());
	}	
	
	
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

}
