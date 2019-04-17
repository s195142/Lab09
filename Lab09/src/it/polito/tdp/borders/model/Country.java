package it.polito.tdp.borders.model;

public class Country {
	
	private String stateAbb;
	private int code;
	private String nome;
	
	
	public Country(String stateAbb, int code, String nome) {
		this.stateAbb = stateAbb;
		this.code = code;
		this.nome = nome;
	}


	public String getStateAbb() {
		return stateAbb;
	}


	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Country: "+stateAbb+" "+nome;
	}
	
	

}
