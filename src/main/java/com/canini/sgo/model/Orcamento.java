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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_finalizacao")
	private Date dataFinalizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_edicao")
	private Date dataEdicao;

	@Enumerated(EnumType.STRING)
	@Column
	private StatusOrcamento statusorcamento = StatusOrcamento.ANDAMENTO;

	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemOrcamento> itens = new ArrayList<>();

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "formapagamento")
	private FormaPagamento formapag;
	
	@Column(name = "prazoentrega")
	private String prazoEntrega;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(columnDefinition = "text")
	private String obs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Date dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public StatusOrcamento getStatusorcamento() {
		return statusorcamento;
	}

	public void setStatusorcamento(StatusOrcamento statusorcamento) {
		this.statusorcamento = statusorcamento;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}	

	public FormaPagamento getFormapag() {
		return formapag;
	}

	public void setFormapag(FormaPagamento formapag) {
		this.formapag = formapag;
	}	
	

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
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
		Orcamento other = (Orcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	public boolean isEmitido() {
		return StatusOrcamento.EMITIDO.equals(getStatusorcamento());
	}
	
	@Transient
	public boolean isAprovado() {
		return StatusOrcamento.APROVADO.equals(getStatusorcamento());
	}
	
	@Transient
	public boolean isAberto() {
		return !isEmitido();
	}

	@Transient
	public BigDecimal getValorSubTotal() {
		return this.getValorTotal().add(this.getValorDesconto());

	}

	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;


		for (ItemOrcamento item : this.getItens()) {
			if (item.getProduto() != null && item.getProduto().getId() != null) {
				total = total.add(item.getValorTotal());
			}

		}

		this.setValorTotal(total);

	}

	public void adicionarItemVazio() {
		if (this.isAndamento()) {

			ItemOrcamento item = new ItemOrcamento();
			item.setProduto(new Produto());
			item.setOrcamento(this);
			this.getItens().add(0, item);
		}

	}

	@Transient
	public boolean isAndamento() {
		return StatusOrcamento.ANDAMENTO.equals(this.getStatusorcamento());
	}
	

	public void removerItemVazio() {
		ItemOrcamento primeiroItem = this.getItens().get(0);

		if (primeiroItem != null && primeiroItem.getProduto() != null && primeiroItem.getProduto().getId() == null) {
			this.getItens().remove(0);
		}

	}
	
	@Transient
	public boolean isValorTotalNegativo() {
		return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
	}

}
