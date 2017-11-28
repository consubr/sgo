package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.canini.sgo.DAO.PermissaoDAO;
import com.canini.sgo.model.Permissao;
import com.canini.sgo.util.CDIServiceLocator;

@FacesConverter("permissaoConverter")
public class PermissaoConverter implements Converter {
	
	private PermissaoDAO permissaoDAO;
	
	public PermissaoConverter() {
		this.permissaoDAO = CDIServiceLocator.getBean(PermissaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Permissao retorno = null;

		if (value != null) {
			retorno = this.permissaoDAO.porId(new Long(value));
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Permissao) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}


}
