package com.canini.sgo.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.canini.sgo.annotations.OrcamentoEdicao;
import com.canini.sgo.model.Orcamento;
import com.canini.sgo.util.FacesUtil;
import com.canini.sgo.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

@Named
@RequestScoped
public class EnvioOrcamentoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@OrcamentoEdicao
	private Orcamento orcamento;
	
	public void enviar() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.orcamento.getCliente().getEmail())
			.subject("Seu Orcamento com a Canini Movelaria!")
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/orcamento.template")))
			.put("orcamento", this.orcamento)
			.put("numberTool", new NumberTool())
			.put("locale",new Locale("pt", "BR"))
			.send();
		
		
		FacesUtil.addInfoMessage("E-mail enviado com sucesso para o cliente!");
		
	}

}
