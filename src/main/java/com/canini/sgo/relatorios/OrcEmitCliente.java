package com.canini.sgo.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.canini.sgo.util.FacesUtil;
import com.canini.sgo.util.JPAUtil;
import com.canini.sgo.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class OrcEmitCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idCliente;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private JPAUtil jpaUtil;
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cliente_id", this.idCliente);

		ExecutorRelatorio executor = new ExecutorRelatorio("/reports/orc_emitido_cliente.jasper",
				this.response, parametros, "Orçamentos Emitidos por Cliente.pdf");

		Session session = jpaUtil.createEntityManager().unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}

	@NotNull
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}