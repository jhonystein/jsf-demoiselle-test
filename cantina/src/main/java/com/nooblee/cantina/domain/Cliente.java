package com.nooblee.cantina.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="CLIENTES")
@TableGenerator(name="clienteGen", table="SENQUENCIAS", 
		pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", 
		pkColumnValue="CLIENTE_ID", allocationSize=1)
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="clienteGen")
	@Column(name="clienteid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cantinaid")
	private Cantina cantina;
	
	@ManyToOne
	@JoinColumn(name="empresaid")
	private Empresa empresa;
	
	@Column(name="nome", length=60)
	private String nome;
	
	@Column(name="documento", length=20)
	private String documento;
	
	@Column(name="email", length=120)
	private String email;
	
	@Column(name="codigo", length=20)
	private String codigo;
	
	@Column(name="telefone", length=20)
	private String telefone;
	
	@Column(name="senha", length=20)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cantina getCantina() {
		return cantina;
	}

	public void setCantina(Cantina cantina) {
		this.cantina = cantina;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
