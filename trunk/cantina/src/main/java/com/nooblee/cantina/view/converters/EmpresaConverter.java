package com.nooblee.cantina.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.util.Beans;

import com.nooblee.cantina.business.EmpresaBC;
import com.nooblee.cantina.domain.Empresa;

@FacesConverter(forClass=Empresa.class)
public class EmpresaConverter implements Converter {

	@Inject
	private EmpresaBC empresaBC = Beans.getReference(EmpresaBC.class);
	
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		return empresaBC.load(Long.parseLong(valor));
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		return ((Empresa)valor).getId().toString();
	}

}
