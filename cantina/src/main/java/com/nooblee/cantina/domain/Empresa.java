package com.nooblee.cantina.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="EMPRESAS")
@TableGenerator(name="empresaGen", table="SENQUENCIAS", 
		pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", 
		pkColumnValue="EMPRESA_ID", allocationSize=1)
public class Empresa {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="empresaGen")
	@Column(name="empresaid")
	private Long id;
	@Column(name="nome", length=60)
	private String nome;
	@Column(name="usuario", length=20)
	private String usuario;
	@Column(name="senha", length=20)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
