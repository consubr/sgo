package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.canini.sgo.DAO.UsuarioDAO;
import com.canini.sgo.model.Usuario;
import com.canini.sgo.util.CDIServiceLocator;

@FacesConverter(forClass=Usuario.class)
public class UsuarioOrcamentoConverter implements Converter {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioOrcamentoConverter() {
		this.usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarioDAO.porId(new Long(value));
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Usuario) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}


}
