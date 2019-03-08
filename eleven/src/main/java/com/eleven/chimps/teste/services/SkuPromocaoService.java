
package com.eleven.chimps.teste.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eleven.chimps.teste.domain.Promocao;
import com.eleven.chimps.teste.domain.SkuPromocao;
import com.eleven.chimps.teste.repositories.SkuPromocaoRepository;
import com.eleven.chimps.teste.services.exceptions.DataIntegrityException;
import com.eleven.chimps.teste.services.exceptions.ObjectNotFoundException;

@Service
public class SkuPromocaoService {
	
	@Autowired
	private SkuPromocaoRepository repo;
	
	public SkuPromocao buscar(Integer id) {
		Optional<SkuPromocao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Promoção não encontrada! Id: %1$s Tipo: %2$s", id, SkuPromocao.class.getName())
			));
	}
	
	public List<SkuPromocao> findAll() {
		return repo.findAll();
	}
	
	public SkuPromocao insert(SkuPromocao skuProm) {
		SkuPromocao obj = repo.save(skuProm);
		return obj;
	}
	
	public void InsertSku(List<SkuPromocao> listSkuPromocao, Promocao objProm) {
		for(SkuPromocao item : listSkuPromocao)
		{
			item.getPromocoes().addAll(Arrays.asList(objProm));
			repo.saveAll(Arrays.asList(item));
		}
	}
	
	public void insertList(List<SkuPromocao> skuProm) {
		for(SkuPromocao item : skuProm)
		{
			repo.save(item);
		}
	}
	
	public SkuPromocao find(Integer id) {
		Optional<SkuPromocao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Sku Promoção não encontrada! Id: %1$s Tipo: %2$s", id, SkuPromocao.class.getName())
			));
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir promoções com vínculos com SKUs, somente atualizar!");
		}
	}
}