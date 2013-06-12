package com.nooblee.cantina.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import com.nooblee.cantina.business.MovimentoBC;
import com.nooblee.cantina.domain.Movimento;

@ViewController
@NextView("./movimento_edit.jsf")
@PreviousView("./movimento_list.jsf")
public class MovimentoListMB extends AbstractListPageBean<Movimento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentoBC movimentoBC;
	
	@Override
	protected List<Movimento> handleResultList() {
		return this.movimentoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				movimentoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}