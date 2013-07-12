package com.nooblee.cantina.persistence;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import com.nooblee.cantina.domain.Cliente;

@PersistenceController
public class ClienteDAO extends JPACrud<Cliente, Long> {

	private static final long serialVersionUID = 1L;

	public List<Cliente> findByNome(String nome) {
		return findByJPQL("select c from " + getBeanClass().getSimpleName() + " c where c.nome like '%" + nome + "%'");
	}

	public List<Cliente> findByDocumento(String documento) {
		return findByJPQL("select c from " + getBeanClass().getSimpleName() + " c where c.documento = '" + documento + "'");
	}
	
	public List<Cliente> findByCodigo(String codigo) {
		return findByJPQL("select c from " + getBeanClass().getSimpleName() + " c where c.codigo = '" + codigo + "'");
	}
	
	@Override
	public List<Cliente> findAll() {
		return findByJPQL("select c from " + getBeanClass().getSimpleName() + " c order by c.nome");
	}
	
}
