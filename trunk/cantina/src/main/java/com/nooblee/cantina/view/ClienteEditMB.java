package com.nooblee.cantina.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

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

	private String novaSenha;
	
	public String getNovaSenha() {
		return novaSenha;
	}
	
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
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
	
	@Transactional
	public void alterarSenha(ActionEvent ae) {
		
		boolean serverValidation = false;
		FacesMessage msg = null;
		
		try {
			if (StringUtils.isNotEmpty(novaSenha)) {
				getBean().setSenha(novaSenha);
				this.clienteBC.update(getBean());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!!!", "");
				serverValidation = true;
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha não pode ser em branco!!!", "");
			}
		} catch (Exception ex) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na alteração da senha!!!", ex.getMessage());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().addCallbackParam("serverValidation", serverValidation);
	}


}