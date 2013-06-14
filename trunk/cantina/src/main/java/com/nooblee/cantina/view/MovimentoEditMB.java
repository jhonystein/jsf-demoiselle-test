package com.nooblee.cantina.view;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
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
		
		boolean loggedIn = false;
		FacesMessage msg = null;
		
		try {
			if (StringUtils.isEmpty(senhaCliente)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha não pode ser em branco!!!", "");
			} else if (!senhaCliente.equals(getBean().getCliente().getSenha())) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!!!", "");
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário logado com sucesso!!!", "");
				loggedIn = true;
				lancarDebito();
			}
		} catch (Exception ex) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na autenticação da senha!!!", ex.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);

	}
	
	@Transactional
	public void alterarSenha(ActionEvent evt) {
		
		boolean loggedIn = false;
		FacesMessage msg = null;
		
		try {
			if (StringUtils.isEmpty(senhaCliente)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha não pode ser em branco!!!", "");
			} else if (!senhaCliente.equals(getBean().getCliente().getSenha())) {
				Cliente c = getBean().getCliente();
				c.setSenha(senhaCliente);
				clienteBC.update(c);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!!!", "");
				loggedIn = true;
				lancarDebito();
			}
		} catch (Exception ex) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na atualização da senha!!!", ex.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);

		
	}
	
	public void buscaCliente(ValueChangeEvent evt) {
		logger.debug("Teste... " + this.codigoCliente);
		List<Cliente> clientes = clienteBC.findByAnything(evt.getNewValue().toString());
		if (clientes.size() == 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente não encontrado!!!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg );
		} else if (clientes.size() == 1) {
			Cliente c = clientes.get(0);
			getBean().setCliente(c);
		} else {
			// aparecer a janela de seleção
		}
	}

}