package br.senai.sp.info.gerenciadepjs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Projeto {

	@Id
	private Long id;

	@ManyToMany
	private List<Tecnologia> tecnologia;

	@ManyToOne
	@JoinColumn(nullable = false, name = "status_id")
	private Status status;

	@Column(length = 40, nullable = false, unique = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String nome;

	@Column(length = 64, nullable = false, unique = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String responsavelBRQ;

	@Column(length = 64, nullable = false, unique = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String responsavelCliente;

	@Column(nullable = true)
	private Float horas;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private Date dataInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private Date dataFim;

	@Lob
	@Column(nullable = true)
	@Size(max = 500, message = "O campo não pode ultrapassar o limite de 500 caractéres.")
	private String descricao;

	// G&S

	// public Tecnologia getTecnologia() {
	// return tecnologia;
	// }
	//
	// public void setTecnologia(Tecnologia tecnologia) {
	// this.tecnologia = tecnologia;
	// }

	public Long getId() {
		return id;
	}

	public List<Tecnologia> getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(List<Tecnologia> tecnologia) {
		this.tecnologia = tecnologia;
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

	public String getResponsavelBRQ() {
		return responsavelBRQ;
	}

	public void setResponsavelBRQ(String responsavelBRQ) {
		this.responsavelBRQ = responsavelBRQ;
	}

	public String getResponsavelCliente() {
		return responsavelCliente;
	}

	public void setResponsavelCliente(String responsavelCliente) {
		this.responsavelCliente = responsavelCliente;
	}

	public Float getHoras() {
		return horas;
	}

	public void setHoras(Float horas) {
		this.horas = horas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
