package com.canini.sgo.relatorios;

import java.awt.Image;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.Session;

import com.canini.sgo.annotations.OrcamentoEdicao;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.util.JPAUtil;
import com.canini.sgo.util.report.ExecOrcamento;

@Named
@RequestScoped
public class OrcImp implements Serializable {

	private static final long serialVersionUID = 1L;

	// private Long idOrcamento;

	@Inject
	@OrcamentoEdicao
	private Orcamento orcamento;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private JPAUtil jpaUtil;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		Image logo = new ImageIcon(getClass().getResource("/reports/logoreport.png")).getImage();
		parametros.put("logo", logo);
		parametros.put("orcamento_id", this.orcamento.getId());

		ExecOrcamento executor = new ExecOrcamento("/reports/orcamentoimp.jasper", this.response, parametros,
				"Orçamento - " + this.orcamento.getId() + " - " + this.orcamento.getCliente().getNome() + ".pdf");

		Session session = jpaUtil.createEntityManager().unwrap(Session.class);
		session.doWork(executor);

		facesContext.responseComplete();

	}

	// public Long getIdOrcamento() {
	// return idOrcamento;
	// }
	//
	// public void setIdOrcamento(Long idOrcamento) {
	// this.idOrcamento = idOrcamento;
	// }

}