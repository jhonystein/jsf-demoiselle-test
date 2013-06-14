package com.nooblee.cantina.view;

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

import com.nooblee.cantina.business.CantinaBC;
import com.nooblee.cantina.domain.Cantina;

@ViewController
@PreviousView("./cantina_list.jsf")
public class CantinaEditMB extends AbstractEditPageBean<Cantina, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CantinaBC cantinaBC;
	
	private String novaSenha;
	
	public String getNovaSenha() {
		return novaSenha;
	}
	
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	@Override
	@Transactional
	public String delete() {
		this.cantinaBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.cantinaBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.cantinaBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.cantinaBC.load(getId()));
	}

	@Transactional
	public void alterarSenha(ActionEvent ae) {

		boolean serverValidation = false;
		FacesMessage msg = null;

		try {
			if (StringUtils.isNotEmpty(novaSenha)){
				getBean().setSenha(novaSenha);
				this.cantinaBC.update(getBean());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!!!", null);
				serverValidation = true;
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha não pode ser em branco!!!", null);
			}
		} catch (Exception ex) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na alteração da senha!!!", ex.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().addCallbackParam("serverValidation", serverValidation);
	}
	
}