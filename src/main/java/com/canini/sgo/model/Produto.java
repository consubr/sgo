package com.canini.sgo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private ClasseProduto classe;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private TipoProduto tipoproduto;
	
	@Column(name = "valor_precobase", precision = 10, scale = 2)
	private BigDecimal precobase;
	
	@Column(name = "valor_precofinal", precision = 10, scale = 2)
	private BigDecimal precofinal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date criacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_edicao")
	private Date edicao;
	
	public boolean isInclusao() {
		return getId() == null ? true : false;
	}

	public boolean isEdicao() {
		return !isInclusao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClasseProduto getClasse() {
		return classe;
	}

	public void setClasse(ClasseProduto classe) {
		this.classe = classe;
	}

	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}

	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}

	public BigDecimal getPrecobase() {
		return precobase;
	}

	public void setPrecobase(BigDecimal precobase) {
		this.precobase = precobase;
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

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecofinal() {
		return precofinal;
	}

	public void setPrecofinal(BigDecimal precofinal) {
		this.precofinal = precofinal;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
