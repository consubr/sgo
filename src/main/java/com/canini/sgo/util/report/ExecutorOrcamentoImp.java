package com.canini.sgo.util.report;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

import com.github.adminfaces.template.exception.BusinessException;

public class ExecutorOrcamentoImp implements Work {

    private String caminhoRelatorio;
    private HttpServletResponse response;
    private Map<String, Object> parametros;
    private String nomeArquivoSaida;
    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private boolean relatorioGerado;

    public ExecutorOrcamentoImp(String caminhoRelatorio,
            HttpServletResponse response, Map<String, Object> parametros,
            String nomeArquivoSaida) {
        this.caminhoRelatorio = caminhoRelatorio;
        this.response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        this.parametros = parametros;
        this.nomeArquivoSaida = nomeArquivoSaida;

        this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
    }


    @Override
    public void execute(Connection connection) throws BusinessException {
        try {
            InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);

            JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
            this.relatorioGerado = print.getPages().size() > 0;
                OutputStream out = response.getOutputStream();
            if (this.relatorioGerado) {
                JRExporter exportador = new JRPdfExporter();
                exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, out );
                exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);


                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"" 
                        + this.nomeArquivoSaida  + "\"");

                exportador.exportReport();
                out.flush();
                                out.close();
                }
        } catch (Exception e) {
            throw new BusinessException("Erro ao executar relatório " + this.caminhoRelatorio);
        }
    }


    public boolean isRelatorioGerado() {
        return relatorioGerado;
    }

}