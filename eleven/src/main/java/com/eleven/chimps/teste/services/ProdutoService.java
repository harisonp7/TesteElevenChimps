package com.eleven.chimps.teste.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleven.chimps.teste.domain.Produto;
import com.eleven.chimps.teste.repositories.ProdutoRepository;
import com.eleven.chimps.teste.services.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Produto não encontrado! Id: %1$s Tipo: %2$s", id, Produto.class.getName())
			));
		}
}
