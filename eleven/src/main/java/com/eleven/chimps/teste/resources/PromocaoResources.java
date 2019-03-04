package com.eleven.chimps.teste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eleven.chimps.teste.domain.Promocao;
import com.eleven.chimps.teste.domain.SkuPromocao;
import com.eleven.chimps.teste.dto.PromocaoDTO;
import com.eleven.chimps.teste.services.PromocaoService;
import com.eleven.chimps.teste.services.SkuPromocaoService;

@RestController
@RequestMapping(value="/promocao")
public class PromocaoResources {
	
	@Autowired
	private PromocaoService service;
	
	@Autowired
	private SkuPromocaoService serviceSku;
	
	//Teste, buscar penas promoção esta ok
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Promocao> find(@PathVariable Integer id) {
		
		Promocao obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	//Inserir apenas promoção, ok
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody PromocaoDTO objDto) {
		Promocao obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();		  
		return ResponseEntity.created(uri).build();
		
	} 
	
	//AQUI UM DOS PROBLEMA, INSERIR UMA PROMOÇÃO COM UMA LISTA DE SKUS - ESTOU ENROLLADO AQUI
	@RequestMapping(value="/proSku", method=RequestMethod.POST)
	public ResponseEntity<Promocao> insertProSku(@Valid @RequestBody Promocao obj) {
		
		for(SkuPromocao item : obj.getSkuPromocoes())
		{
			serviceSku.insert(item);
		}
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();		  
		return ResponseEntity.created(uri).build();
		
	} 
	
	//Testei o update de promoção ok
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@Valid @RequestBody PromocaoDTO objDto, @PathVariable Integer id ) {
		Promocao obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);	  
		return ResponseEntity.noContent().build();
		
	}
	//Delete de Promoção, esta ok
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Listando todas as promoções ok
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PromocaoDTO>> findAll() {
		
		List<Promocao> list = service.findAll();
		List<PromocaoDTO> listDto = list.stream().map(obj -> new PromocaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	//Neste caso eu vou listar as promoções com os SKUs, esta ok.. 
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public ResponseEntity<List<Promocao>> findAll2() {
		
		List<Promocao> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	//Lista com paginação, esta ok...
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PromocaoDTO>> findPage(
			@RequestParam(value="Page", defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		Page<Promocao> list = service.findPage(page,linesPerPage, orderBy, direction);
		Page<PromocaoDTO> listDto = list.map(obj -> new PromocaoDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}

