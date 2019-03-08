package com.eleven.chimps.teste.resources;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.eleven.chimps.teste.domain.SkuPromocao;
import com.eleven.chimps.teste.services.SkuPromocaoService;

@RestController
@RequestMapping(value="/skuPromocao")
public class SkuPromocaoResources {
	
	@Autowired
	private SkuPromocaoService serviceSku;
	
	/*
	 * @version   : 0.1 
	 * @author    : Harison Carvalho
	 * @internal  : busca promoções por id
	 * @name      : find type GET
	 * @param     : id Integer
	 * @return    : 200 - OK
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<SkuPromocao> find(@PathVariable Integer id) {
		SkuPromocao obj = serviceSku.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * @version   : 0.1 
	 * @author    : Harison Carvalho
	 * @internal  : insere SKU 
	 * @name      : insert type POST
	 * @param     : id Integer
	 * @return    : URI - EndPoint com ID do cadastro
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody SkuPromocao obj) {
		obj = serviceSku.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();		  
		return ResponseEntity.created(uri).build();
	} 
	
	/*
	 * @version   : 0.1 
	 * @author    : Harison Carvalho
	 * @internal  : Deleta SKU por id
	 * @name      : delete type DELETE
	 * @param     : id Integer
	 * @return    : 200 - OK
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		serviceSku.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * @version   : 0.1 
	 * @author    : Harison Carvalho
	 * @internal  : Lista todos os SKUs cadastrados
	 * @name      : findAll type GET
	 * @param     : id Integer
	 * @return    : obj SKU Promocao -> Id, SKU
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SkuPromocao>> findAll() {
		List<SkuPromocao> list = serviceSku.findAll();
		return ResponseEntity.ok().body(list);	
	}
}

