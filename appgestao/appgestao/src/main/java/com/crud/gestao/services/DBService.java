package com.crud.gestao.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.gestao.domain.Chamado;
import com.crud.gestao.domain.Cliente;
import com.crud.gestao.domain.Colaborador;
import com.crud.gestao.domain.enums.Prioridade;
import com.crud.gestao.domain.enums.Status;
import com.crud.gestao.domain.enums.perfil;
import com.crud.gestao.repositories.ChamadoRepository;
import com.crud.gestao.repositories.ClienteRepository;
import com.crud.gestao.repositories.ColaboradorRepository;

@Service
public class DBService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	@Autowired
	private ClienteRepository clienterepository;
	@Autowired
	private ChamadoRepository chamadorepository;

	
	public void instanciaDB() {
		Colaborador col1 = new Colaborador(null, "rafa", "98824640095", "rafa@teste.com.br", "123");
		col1.addPerfil(perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "linus", "69360557021", "rafatestecliente@teste.com.br", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", col1, cli1);
		
		
		colaboradorRepository.saveAll(Arrays.asList(col1));
		clienterepository.saveAll(Arrays.asList(cli1));
		chamadorepository.saveAll(Arrays.asList(c1));

	}

}
