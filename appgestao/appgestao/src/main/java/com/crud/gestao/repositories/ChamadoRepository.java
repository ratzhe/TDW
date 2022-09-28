package com.crud.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestao.domain.Chamado;
 
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
