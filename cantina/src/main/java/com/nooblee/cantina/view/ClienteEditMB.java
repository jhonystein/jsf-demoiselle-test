package com.nooblee.cantina.view;

import java.util.List;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.business.EmpresaBC;
import com.nooblee.cantina.domain.Cliente;
import com.nooblee.cantina.domain.Empresa;

@ViewController
@PreviousView("./cliente_list.jsf")
public class ClienteEditMB extends AbstractEditPageBean<Cliente, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBC clienteBC;
	
	
	@Inject
	private EmpresaBC empresaBC;

	public List<Empresa> getEmpresas() {
		return empresaBC.findAll();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.clienteBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.clienteBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.clienteBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.clienteBC.load(getId()));
	}

}