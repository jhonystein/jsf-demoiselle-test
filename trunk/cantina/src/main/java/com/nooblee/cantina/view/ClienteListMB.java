﻿package com.nooblee.cantina.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.business.ClienteBC;
import com.nooblee.cantina.domain.Cliente;

@ViewController
@NextView("./cliente_edit.jsf")
@PreviousView("./cliente_list.jsf")
public class ClienteListMB extends AbstractListPageBean<Cliente, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBC clienteBC;
	
	@Override
	protected List<Cliente> handleResultList() {
		return this.clienteBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				clienteBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
}