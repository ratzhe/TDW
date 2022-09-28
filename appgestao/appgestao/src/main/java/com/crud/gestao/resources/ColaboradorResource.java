package com.crud.gestao.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crud.gestao.domain.Colaborador;
import com.crud.gestao.domain.dtos.ColaboradorDTO;
import com.crud.gestao.services.ColaboradorService;

@RestController
@RequestMapping(value = "/colaboradores")
public class ColaboradorResource {
	
	
	@Autowired
	private ColaboradorService service;
	
	//busca o colaborador pelo id e retorna o obj do colaborador
	@GetMapping(value = "/{id}")
	public ResponseEntity<ColaboradorDTO> findById(@PathVariable Integer id){
		Colaborador obj = service.findById(id);
		return ResponseEntity.ok().body(new ColaboradorDTO(obj));
		
	}
	
	
	//Busca os colaboradores sem o id ( todos) 
	@GetMapping
	public ResponseEntity<List<ColaboradorDTO>> findAll(){
		List<Colaborador> list = service.findAll();
		List<ColaboradorDTO> listDTO = list.stream().map(obj -> new ColaboradorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	//Cadastra um novo colaborador
	@PostMapping
	public ResponseEntity<ColaboradorDTO> create(@Valid @RequestBody ColaboradorDTO objDTO){
		Colaborador newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	//Atualiza um colaborador
	@PutMapping(value = "/{id}")
	public ResponseEntity<ColaboradorDTO> update(@PathVariable Integer id, @Valid @RequestBody ColaboradorDTO objDTO ){
		Colaborador obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ColaboradorDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ColaboradorDTO> delete(@PathVariable Integer id){
			service.delete(id);
			
			return ResponseEntity.noContent().build();
		
	}

}
