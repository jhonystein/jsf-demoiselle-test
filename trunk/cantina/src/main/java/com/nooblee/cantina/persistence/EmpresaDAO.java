package com.nooblee.cantina.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.nooblee.cantina.domain.Empresa;

@PersistenceController
public class EmpresaDAO extends JPACrud<Empresa, Long> {

	private static final long serialVersionUID = 1L;
	

}
