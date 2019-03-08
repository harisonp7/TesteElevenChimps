package com.eleven.chimps.teste.domain;

public class Calculated {
	
	private String sku;
	private Double prOriginal;
	private Double prPromocional;
	
	public Calculated(String sku, Double prOriginal, Double prPromocional) {
		super();
		this.sku = sku;
		this.prOriginal = prOriginal;
		this.prPromocional = prPromocional;
	}
	
	public Calculated() {
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Double getPrOriginal() {
		return prOriginal;
	}
	public void setPrOriginal(Double prOriginal) {
		this.prOriginal = prOriginal;
	}
	public Double getPrPromocional() {
		return prPromocional;
	}
	public void setPrPromocional(Double prPromocional) {
		this.prPromocional = prPromocional;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prOriginal == null) ? 0 : prOriginal.hashCode());
		result = prime * result + ((prPromocional == null) ? 0 : prPromocional.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Calculated other = (Calculated) obj;
		if (prOriginal == null) {
			if (other.prOriginal != null)
				return false;
		} else if (!prOriginal.equals(other.prOriginal))
			return false;
		if (prPromocional == null) {
			if (other.prPromocional != null)
				return false;
		} else if (!prPromocional.equals(other.prPromocional))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

}
