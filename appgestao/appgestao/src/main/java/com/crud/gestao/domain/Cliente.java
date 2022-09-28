package com.crud.gestao.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.crud.gestao.domain.dtos.ClienteDTO;
import com.crud.gestao.domain.enums.perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Cliente extends Pessoa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados  = new ArrayList<>();

	public Cliente() {
		super();	
		addPerfil(perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(perfil.CLIENTE);

	}
	
	public Cliente(ClienteDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.datacriacao = obj.getDatacriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
