package com.nooblee.cantina.business;

import java.util.List;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.nooblee.cantina.domain.Cliente;
import com.nooblee.cantina.persistence.ClienteDAO;

@BusinessController
public class ClienteBC extends DelegateCrud<Cliente, Long, ClienteDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void inicializa() {
		if (findAll().size() == 0) {
			Cliente c = new Cliente();
			c.setNome("Jhony Alceu Pereira");
			c.setCodigo("68695");
			c.setSenha("123");
			this.insert(c);
		}
	}
	
	public List<Cliente> findByAnything(String anything) {
		return getDelegate().findByCodigo(anything);
	}
	
}
