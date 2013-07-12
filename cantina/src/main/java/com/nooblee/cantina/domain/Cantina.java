package com.nooblee.cantina.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="CANTINAS")
@TableGenerator(name="cantinaGen", table="SENQUENCIAS", 
		pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", 
		pkColumnValue="CANTINA_ID", allocationSize=1)
public class Cantina {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="cantinaGen")
	@Column(name="cantinaid")
	private Long id;
	
	@Column(name="uuid", unique=true)
	private String uuid;
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="email", length=120)
	private String email;
	
	@Column(name="senha", length=20)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
