package com.crud.gestao.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.gestao.domain.Cliente;
import com.crud.gestao.domain.Pessoa;
import com.crud.gestao.domain.dtos.ClienteDTO;
import com.crud.gestao.repositories.ClienteRepository;
import com.crud.gestao.repositories.PessoaRepository;
import com.crud.gestao.services.exceptions.DataIntegrityViolationException;
import com.crud.gestao.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	
	//procura o cliente pelo id e da um response formatado caso não exista o id no banco
	@Autowired
	private PessoaRepository pessoarepository;
	public Cliente findById(Integer id) {
		Optional<Cliente> obj  = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" +id));
	}
	
	//Lista todos os cliente quando for sem o id 
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	//cria um cliente
	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfeEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}
	
	
	//Atualiza um cliente e verifica o cpf e email se são campos validos 
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfeEmail(objDTO);																																																												
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
   
	
	//Deleta um cliente, mas antes verifica se ele não possui uma ordem de serviço em aberto 
    public void delete(Integer id) {
    	Cliente obj = findById(id);
    	if(obj.getChamados().size() > 0 ) {
    		throw new DataIntegrityViolationException("O Cliente possui uma order de serviço e não pode ser deletado");
    	}
    		repository.deleteById(id);
    	
	}
	
	//Valida se os dados já estão cadastrados no sistema
	private void validaPorCpfeEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoarepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema");  // caso já exista o cpf
		}
		obj = pessoarepository.findByEmail(objDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail  já cadastrado no sistema"); // caso já exista o email
		}

	}

	
	
}
