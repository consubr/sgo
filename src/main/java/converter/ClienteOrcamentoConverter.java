package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.canini.sgo.DAO.ClienteDAO;
import com.canini.sgo.model.Cliente;
import com.canini.sgo.util.CDIServiceLocator;

@FacesConverter(forClass=Cliente.class)
public class ClienteOrcamentoConverter implements Converter {
	
	private ClienteDAO clienteDAO;
	
	public ClienteOrcamentoConverter() {
		this.clienteDAO = CDIServiceLocator.getBean(ClienteDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null) {
			retorno = this.clienteDAO.porId(new Long(value));
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Cliente) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}


}
