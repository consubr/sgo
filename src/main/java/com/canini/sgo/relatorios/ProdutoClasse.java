package com.canini.sgo.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.canini.sgo.util.FacesUtil;
import com.canini.sgo.util.JPAUtil;
import com.canini.sgo.util.report.ExecProdClasse;

@Named
@RequestScoped
public class ProdutoClasse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String classe;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private JPAUtil jpaUtil;
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("classe", this.classe);

		ExecProdClasse executor = new ExecProdClasse("/reports/produto_tipo.jasper",
				this.response, parametros, "Produtos por Classe.pdf");

		Session session = jpaUtil.createEntityManager().unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	

}