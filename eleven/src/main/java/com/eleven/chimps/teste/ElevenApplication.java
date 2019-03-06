package com.eleven.chimps.teste;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eleven.chimps.teste.domain.Produto;
import com.eleven.chimps.teste.domain.Promocao;
import com.eleven.chimps.teste.domain.SkuPromocao;
import com.eleven.chimps.teste.repositories.ProdutoRepository;
import com.eleven.chimps.teste.repositories.PromocaoRepository;
import com.eleven.chimps.teste.repositories.SkuPromocaoRepository;

@SpringBootApplication
public class ElevenApplication implements CommandLineRunner{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Autowired
	private SkuPromocaoRepository skuPromocaoRepository;

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(ElevenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
		Produto ObjProd = new Produto(null,"Camisa","CAMLEVVP","Levis","Vermelha","G",Double.parseDouble("200"));
		Produto ObjProd1 = new Produto(null,"Camisa","CAMLEVVP","Levis","Vermelha","GG",Double.parseDouble("350"));
		
		produtoRepository.saveAll(Arrays.asList(ObjProd, ObjProd1));
		
		SkuPromocao objSku = new SkuPromocao(null,"CAMLEVVERP");
		SkuPromocao objSku1 = new SkuPromocao(null,"CAMLEVAMAG");
//		SkuPromocao objSku2 = new SkuPromocao(null,"CAMLEVVERG");
//		
//		SkuPromocao objSku3 = new SkuPromocao(null,"CAMLEVPREP");
//		SkuPromocao objSku4 = new SkuPromocao(null,"CAMLEVPREM");
//		SkuPromocao objSku5 = new SkuPromocao(null,"CAMLEVPREG");
		
		Promocao objProm = new Promocao(null,"Promoção de Carnaval",30);
//		Promocao objProm1 = new Promocao(null,"Promocao de Natal",40);
//		Promocao objProm2 = new Promocao(null,"Promocao de Dias das Crianças",70);
		
		
		objProm.getSkuPromocoes().addAll(Arrays.asList(objSku, objSku1));
//		objProm1.getSkuPromocoes().addAll(Arrays.asList(objSku, objSku5));
//		objProm2.getSkuPromocoes().addAll(Arrays.asList(objSku2, objSku3, objSku4));
		
		objSku.getPromocoes().addAll(Arrays.asList(objProm));
		objSku1.getPromocoes().addAll(Arrays.asList(objProm));
		//objSku.getPromocoes().addAll(Arrays.asList(objProm, objProm1));
//		objSku2.getPromocoes().addAll(Arrays.asList(objProm2));
//		objSku3.getPromocoes().addAll(Arrays.asList(objProm2));
//		objSku4.getPromocoes().addAll(Arrays.asList(objProm2));
//		objSku5.getPromocoes().addAll(Arrays.asList(objProm2));

		
		//promocaoRepository.saveAll(Arrays.asList(objProm, objProm1, objProm2));
		promocaoRepository.saveAll(Arrays.asList(objProm));
		
		//skuPromocaoRepository.saveAll(Arrays.asList(objSku, objSku1, objSku2,objSku3, objSku4, objSku5));
		skuPromocaoRepository.saveAll(Arrays.asList(objSku, objSku1));
		
	}

}
