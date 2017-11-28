package com.canini.sgo.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.canini.sgo.model.Produto;
import com.github.adminfaces.template.exception.BusinessException;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Produto salvar(Produto produto){
		return manager.merge(produto);
	}
	
	public void excluir(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (Exception e) {
			throw new BusinessException("Produto nao pode ser excluido. Pode existir dependencias de informação. Favor verifique!");
		}
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
		
	}
	
	public List<Produto> listAll() {
		return manager.createNativeQuery("SELECT * FROM produto", Produto.class).getResultList();
	}
	
	public List<Produto> listAllMP(){
		return manager.createNativeQuery("SELECT * FROM produto WHERE classe = 'MP'", Produto.class).getResultList();
	}
	
	public List<Produto> listAllPA(){
		return manager.createNativeQuery("SELECT * FROM produto WHERE classe = 'PA'", Produto.class).getResultList();
	}

	public List<Produto> porNome(String nome) {
		return this.manager.createQuery("from Produto where upper(nome) like :nome and classe = 'MP'", Produto.class)
				.setParameter("nome", nome.toUpperCase()+ "%").getResultList();
	}

	public List<Produto> porProduto(String nome) {
		return this.manager.createQuery("from Produto where upper(nome) like :nome and classe = 'PA'", Produto.class)
				.setParameter("nome", nome.toUpperCase()+ "%").getResultList();
	}
}
