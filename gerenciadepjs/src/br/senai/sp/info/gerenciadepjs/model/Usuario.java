package br.senai.sp.info.gerenciadepjs.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.DigestUtils;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario implements Authentication{	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Permissao permissao = Permissao.COORDENADOR;
	
	@Column(length = 30, nullable = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String nome;
	
	@Column(length = 50, nullable = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String sobrenome;
		
	@Column(length = 120, nullable = false, unique = true)
	@NotEmpty(message = "Este campo é obrigatório!")
	@Email
	private String email;
	
	@Column(length = 11, nullable = false, unique = true)
	@Size(min = 8, max = 11, message = "Telefone deve conter entre 8 a 11 caractéres.")
	private String telefone;
	
	@Column(length = 64, nullable = false)
	@NotEmpty(message = "Este campo é obrigatório!")
	private String senha;
	
	//Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}

	//Authentication
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false; //false
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {		
	}
}