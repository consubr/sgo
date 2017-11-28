package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.canini.sgo.DAO.ProdutoDAO;
import com.canini.sgo.model.Produto;
import com.canini.sgo.util.CDIServiceLocator;

@FacesConverter(forClass=Produto.class)
public class ProdutoFichaConverter implements Converter {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoFichaConverter() {
		this.produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;

		if (value != null) {
			retorno = this.produtoDAO.porId(new Long(value));
		}

		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Produto) value).getId();
			String retorno = (id == null ? null : id.toString());
			
			return retorno;
		}
		
		return "";
	}


}
