package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.canini.sgo.DAO.TipoProdutoDAO;
import com.canini.sgo.model.TipoProduto;
import com.canini.sgo.util.CDIServiceLocator;

@FacesConverter(forClass=TipoProduto.class)
public class TipoConverter implements Converter {
	
	private TipoProdutoDAO tipoprodutoDAO;
	
	public TipoConverter() {
		this.tipoprodutoDAO = CDIServiceLocator.getBean(TipoProdutoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoProduto retorno = null;

		if (value != null) {
			retorno = this.tipoprodutoDAO.porId(new Long(value));
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((TipoProduto) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}


}
