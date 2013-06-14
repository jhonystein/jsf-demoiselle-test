package com.nooblee.cantina.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MOVIMENTOS")
@TableGenerator(name="movimentoGen", table="SENQUENCIAS", 
		pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", 
		pkColumnValue="MOVIMENTO_ID", allocationSize=1)
public class Movimento {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="movimentoGen")
	@Column(name = "movimentoid")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "clienteid")
	private Cliente cliente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lancamento")
	private Date lancamento;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "valor", precision=10, scale=2)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getLancamento() {
		return lancamento;
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
