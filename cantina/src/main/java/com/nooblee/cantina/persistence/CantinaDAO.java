package com.nooblee.cantina.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.nooblee.cantina.domain.Cantina;

@PersistenceController
public class CantinaDAO extends JPACrud<Cantina, Long> {

	private static final long serialVersionUID = 1L;
	

}
