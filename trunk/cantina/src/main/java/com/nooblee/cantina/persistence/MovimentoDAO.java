package com.nooblee.cantina.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.nooblee.cantina.domain.Movimento;

@PersistenceController
public class MovimentoDAO extends JPACrud<Movimento, Long> {

	private static final long serialVersionUID = 1L;
	

}
