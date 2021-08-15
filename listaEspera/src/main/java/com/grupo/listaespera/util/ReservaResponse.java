package com.grupo.listaespera.util;

public class ReservaResponse {
	private Long id;
	private Integer numeroPersonas;
	private Integer numeroReserva;
	private Boolean estadoR;
	private String email;
	public ReservaResponse(Long id, Integer numeroPersonas, Integer numeroReserva, Boolean estadoR, String email) {
		super();
		this.id = id;
		this.numeroPersonas = numeroPersonas;
		this.numeroReserva = numeroReserva;
		this.estadoR = estadoR;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(Integer numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	public Integer getNumeroReserva() {
		return numeroReserva;
	}
	public void setNumeroReserva(Integer numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
	public Boolean getEstadoR() {
		return estadoR;
	}
	public void setEstadoR(Boolean estadoR) {
		this.estadoR = estadoR;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
