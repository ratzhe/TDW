package com.crud.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestao.domain.Colaborador;
 
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

}
