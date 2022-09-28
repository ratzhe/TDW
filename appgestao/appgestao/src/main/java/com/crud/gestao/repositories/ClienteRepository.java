package com.crud.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestao.domain.Cliente;
 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
