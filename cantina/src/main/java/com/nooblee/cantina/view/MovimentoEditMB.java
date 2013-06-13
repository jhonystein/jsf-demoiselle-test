package com.nooblee.cantina.view;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.business.MovimentoBC;
import com.nooblee.cantina.domain.Cliente;
import com.nooblee.cantina.domain.Movimento;

@ViewController
public class MovimentoEditMB extends AbstractEditPageBean<Movimento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentoBC movimentoBC;
	
	@Inject
	private ClienteBC clienteBC;
	
	private String codigoCliente;
	
	private String senhaCliente;
	
	private boolean logedIn;
	
	@Inject
	private Logger logger;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getSenhaCliente() {
		return senhaCliente;
	}
	
	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}
	
	public boolean isLogedIn() {
		return logedIn;
	}
	
	public void setLogedIn(boolean logedIn) {
		this.logedIn = logedIn;
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
	
	@Transactional
	public String lancarDebito() {
		getBean().setTipo("D");
		this.movimentoBC.insert(getBean());
		this.codigoCliente="";
		this.setBean(this.createBean());
		return null;
	}
	
	@Override
	protected Movimento createBean() {
		Movimento m = super.createBean();
		m.setLancamento(new Date());
		return m;
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.movimentoBC.load(getId()));
	}
	
	@Transactional
	public void logarCliente(ActionEvent evt) {
		// TODO
	}
	
	@Transactional
	public void alterarSenha(ActionEvent evt) {
		// TODO
	}
	
	public void buscaCliente(ValueChangeEvent evt) {
		logger.debug("Teste... " + this.codigoCliente);
		List<Cliente> clientes = clienteBC.findByAnything(evt.getNewValue().toString());
		if (clientes.size() == 1) {
			Cliente c = clientes.get(0);
			getBean().setCliente(c);
		} else {
			// aparecer a janela de seleção
		}
	}

}