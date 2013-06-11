package com.nooblee.cantina.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import com.nooblee.cantina.domain.Movimento;
import com.nooblee.cantina.persistence.MovimentoDAO;

@BusinessController
public class MovimentoBC extends DelegateCrud<Movimento, Long, MovimentoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
