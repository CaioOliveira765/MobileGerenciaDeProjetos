package br.senai.sp.info.gerenciadepjs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Status {
	
	@Id
	private int id;
	
	@NotNull
	private String nome;
	
	//Getter & Setters
	
	public String getNome() {
		return nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
