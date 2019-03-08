
package com.eleven.chimps.teste.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.eleven.chimps.teste.domain.EmailPromocao;
import com.eleven.chimps.teste.domain.Promocao;
import com.eleven.chimps.teste.repositories.EmailPromocaoRepository;
import com.eleven.chimps.teste.services.exceptions.DataIntegrityException;
import com.eleven.chimps.teste.services.exceptions.ObjectNotFoundException;

@Service
public class EmailPromocaoService {
	
	@Autowired
	private EmailPromocaoRepository repo;
	
	public EmailPromocao buscar(Integer id) {
		Optional<EmailPromocao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Promoção não encontrada! Id: %1$s Tipo: %2$s", id, EmailPromocao.class.getName())
			));
	}
	
	public List<EmailPromocao> findAll() {
		return repo.findAll();
	}
	
	public EmailPromocao insert(EmailPromocao emailProm) {
		EmailPromocao obj = repo.save(emailProm);
		return obj;
	}
	
	public void InsertEmail(List<EmailPromocao> listEmailPromocao, Promocao objProm) {
		for(EmailPromocao item : listEmailPromocao)
		{
			item.getPromocoes().addAll(Arrays.asList(objProm));
			repo.saveAll(Arrays.asList(item));
		}
	}
	
	public void insertList(List<EmailPromocao> emailProm) {
		for(EmailPromocao item : emailProm)
		{
			repo.save(item);
		}
	}
	
	public EmailPromocao find(Integer id) {
		Optional<EmailPromocao> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Sku Promoção não encontrada! Id: %1$s Tipo: %2$s", id, EmailPromocao.class.getName())
			));
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir promoções com vínculos com emails, somente atualizar!");
		}
	}
}