package com.eleven.chimps.teste.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CalcDTO  implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Inserir o e-mail do usuário logado com o tamanho de 5 e 80 caracteres")
	private String email;
	
	/*
	 * Estou colocando como regra para SKU com tamnho máximo de 12 caracteres
	 */
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="Inserir o SKU com o tamanho de 5 e 12 caracteres")
	private String sku;
	
	/*
	 *Estou recebendo o preço origina no formato String, assim eu formato evitando erros com problemas de ponto ou virgula
	 *
	 */
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=1, message="Inserir o preço com o tamanho de 1")
	private String prOrig;
	
	private Integer percentual;
	
	public CalcDTO() {}
	
	public CalcDTO(CalcDTO obj) {
		this.email = obj.getEmail();
		this.sku = obj.getSku();
		this.prOrig = obj.getPrOrig();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPrOrig() {
		return prOrig;
	}

	public void setPrOrig(String prOrig) {
		this.prOrig = prOrig;
	}
}
