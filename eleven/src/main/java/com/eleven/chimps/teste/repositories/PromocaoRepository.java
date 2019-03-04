package com.eleven.chimps.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eleven.chimps.teste.domain.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Integer>{

}
