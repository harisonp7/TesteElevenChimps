package com.eleven.chimps.teste.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmailPromocao  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String email;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "EMAILPROMOCAO_PROMOCAO",
		joinColumns = @JoinColumn(name = "emailPromocao_id"),
		inverseJoinColumns = @JoinColumn(name="promocao_id")
	)
	private List<Promocao> promocoes = new ArrayList<>();
	
	public EmailPromocao(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;	
	}

	public EmailPromocao() {	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String sku) {
		this.email = sku;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailPromocao other = (EmailPromocao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
