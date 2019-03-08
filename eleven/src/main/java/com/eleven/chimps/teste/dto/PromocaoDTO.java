package com.eleven.chimps.teste.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.eleven.chimps.teste.domain.EmailPromocao;
import com.eleven.chimps.teste.domain.Promocao;
import com.eleven.chimps.teste.domain.SkuPromocao;

public class PromocaoDTO  implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Inserir o nome com o tamanho de 5 e 80 caracteres")
	private String nome;
	
	@Max(100)
	@Min(1)
	private Integer percentual;
	
	private List<SkuPromocao> skuPromocoes = new ArrayList<>();
	
	private List<EmailPromocao> emailPromocoes = new ArrayList<>();
	
	public PromocaoDTO() {}
	
	public PromocaoDTO(Promocao obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.percentual = obj.getPercentual();
		this.skuPromocoes = obj.getSkuPromocoes();
		this.emailPromocoes = obj.getEmailPromocoes();
	}
	
	public List<SkuPromocao> getSkuPromocoes() {
		return skuPromocoes;
	}

	public void setSkuPromocoes(List<SkuPromocao> skuPromocoes) {
		for(SkuPromocao item :skuPromocoes)
		{
			this.skuPromocoes.add(item);
		}
	}
	
	public List<EmailPromocao> getEmailPromocoes() {
		return emailPromocoes;
	}

	public void setEmailPromocoes(List<EmailPromocao> emailPromocoes) {
		for(EmailPromocao item :emailPromocoes)
		{
			this.emailPromocoes.add(item);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}

}
