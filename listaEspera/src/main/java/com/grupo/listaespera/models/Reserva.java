package com.grupo.listaespera.models;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reserva")
public class Reserva {
	//------------ Id ----------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// ********** Atributos o Campos ********************
	private Integer numeroPersonas;
	private Integer numeroReserva;
	private Boolean estadoR;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	// ========= Relacion =========
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// -------------------------------------------------------

	public Reserva() {

	}

	public Reserva( Integer numeroPersonas, Integer numeroReserva, boolean estadoR, User user) {

		this.numeroPersonas = numeroPersonas;
		this.numeroReserva = numeroReserva;
		this.estadoR = estadoR;
		this.user = user;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}


}
