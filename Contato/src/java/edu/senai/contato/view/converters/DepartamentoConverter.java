package edu.senai.contato.view.converters;

import edu.senai.contato.model.Departamento;
import edu.senai.contato.view.DepartamentoBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author senai-sc
 */
@FacesConverter(forClass = Departamento.class)
public class DepartamentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return DepartamentoBean.registros.get(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return "" + DepartamentoBean.registros.indexOf(o);
    }
    
}
