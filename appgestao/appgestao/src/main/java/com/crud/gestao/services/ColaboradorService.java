package com.crud.gestao.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.gestao.domain.Colaborador;
import com.crud.gestao.domain.Pessoa;
import com.crud.gestao.domain.dtos.ColaboradorDTO;
import com.crud.gestao.repositories.ColaboradorRepository;
import com.crud.gestao.repositories.PessoaRepository;
import com.crud.gestao.services.exceptions.DataIntegrityViolationException;
import com.crud.gestao.services.exceptions.ObjectnotFoundException;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository repository;
	
	
	//procura o colaborador pelo id e da um response formatado caso não exista o id no banco
	@Autowired
	private PessoaRepository pessoarepository;
	public Colaborador findById(Integer id) {
		Optional<Colaborador> obj  = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id:" +id));
	}
	
	//Lista todos os colaboradores quando for sem o id 
	public List<Colaborador> findAll() {
		return repository.findAll();
	}
	//cria um colaborador
	public Colaborador create(ColaboradorDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfeEmail(objDTO);
		Colaborador newObj = new Colaborador(objDTO);
		return repository.save(newObj);
	}
	
	
	//Atualiza um colaborador e verifica o cpf e email se são campos validos 
	public Colaborador update(Integer id, @Valid ColaboradorDTO objDTO) {
		objDTO.setId(id);
		Colaborador oldObj = findById(id);
		validaPorCpfeEmail(objDTO);																																																												
		oldObj = new Colaborador(objDTO);
		return repository.save(oldObj);
	}
   
	
	//Deleta um colaborador, mas antes verifica se ele não possui uma ordem de serviço em aberto 
    public void delete(Integer id) {
    	Colaborador obj = findById(id);
    	if(obj.getChamados().size() > 0 ) {
    		throw new DataIntegrityViolationException("O Colaborador possui uma order de serviço e não pode ser deletado");
    	}
    		repository.deleteById(id);
    	
	}
	
	//Valida se os dados já estão cadastrados no sistema
	private void validaPorCpfeEmail(ColaboradorDTO objDTO) {
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
