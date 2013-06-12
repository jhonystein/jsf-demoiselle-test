package com.nooblee.cantina.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.domain.Empresa;
import com.nooblee.cantina.persistence.EmpresaDAO;

@BusinessController
public class EmpresaBC extends DelegateCrud<Empresa, Long, EmpresaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void inicializa() {
		if (findAll().size() == 0) {
			Empresa e = new Empresa();
			e.setNome("SENAI");
			e.setUsuario("senai");
			e.setSenha("123");
			this.insert(e);
		}
	}
}
