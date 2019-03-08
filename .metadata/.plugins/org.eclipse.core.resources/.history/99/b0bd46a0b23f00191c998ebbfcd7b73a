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
@RequestMapping(value="/skuPromocao")
public class SkuPromocaoResources {
	
	@Autowired
	private PromocaoService service;
	
	@Autowired
	private SkuPromocaoService serviceSku;
	
	//Listando um SKU esta ok
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<SkuPromocao> find(@PathVariable Integer id) {
		
		SkuPromocao obj = serviceSku.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Inserindo apenas 1 SKUs por vez esta ok
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody SkuPromocao obj) {
		obj = serviceSku.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();		  
		return ResponseEntity.created(uri).build();
		
	} 
	
	@RequestMapping(value="/proSku", method=RequestMethod.POST)
	public ResponseEntity<Promocao> insertProSku(@Valid @RequestBody Promocao obj) {
		
		//Tentei receber o objetvo promocao e salvar os SKUs antes para depois salvar a promoção, não testei, estava nessa parte
		for(SkuPromocao item : obj.getSkuPromocoes())
		{
			serviceSku.insert(item);
		}
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();		  
		return ResponseEntity.created(uri).build();
		
	} 
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		serviceSku.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	// listando ok
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SkuPromocao>> findAll() {
		List<SkuPromocao> list = serviceSku.findAll();
		return ResponseEntity.ok().body(list);	
	}
}

