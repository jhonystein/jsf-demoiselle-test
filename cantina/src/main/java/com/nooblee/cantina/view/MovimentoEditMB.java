package com.nooblee.cantina.view;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.business.MovimentoBC;
import com.nooblee.cantina.domain.Cliente;
import com.nooblee.cantina.domain.Movimento;

@ViewController
@PreviousView("./movimento_list.jsf")
public class MovimentoEditMB extends AbstractEditPageBean<Movimento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentoBC movimentoBC;
	
	@Inject
	private ClienteBC clienteBC;
	
	private String codigoCliente;
	
	@Inject
	private Logger logger;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	@Override
	@Transactional
	public String delete() {
		this.movimentoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.movimentoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.movimentoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.movimentoBC.load(getId()));
	}
	
	public void buscaCliente(ValueChangeEvent evt) {
		logger.debug("Teste... " + this.codigoCliente);
		List<Cliente> clientes = clienteBC.findByAnything(evt.getNewValue().toString());
		if (clientes.size() == 1) {
			getBean().setCliente(clientes.get(0));
			this.codigoCliente = getBean().getCliente().getNome();
		} else {
			// aparecer a janela de seleção
		}
	}

}