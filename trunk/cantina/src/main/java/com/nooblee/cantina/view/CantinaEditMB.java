package com.nooblee.cantina.view;

import javax.inject.Inject;
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

}