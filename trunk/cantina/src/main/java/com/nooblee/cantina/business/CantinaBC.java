package com.nooblee.cantina.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.domain.Cantina;
import com.nooblee.cantina.persistence.CantinaDAO;

@BusinessController
public class CantinaBC extends DelegateCrud<Cantina, Long, CantinaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void inicializa() {
		if (findAll().size() == 0) {
			Cantina c = new Cantina();
			c.setNome("Cia da Refeição");
			c.setUuid("ciadarefeicao");
			c.setSenha("123");
			this.insert(c);
		}
	}
	
}
