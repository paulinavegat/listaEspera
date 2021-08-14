package com.grupo.listaEspera.models;

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
	//********** Atributos o Campos ********************
	//@Min(0)
	//@Size(min = 5, max = 200)
	//private String nombre;
	private Long numeroReserva;
	private Integer numeroPersonas;
	private Boolean estado;
	
	//------------createdAt updatedAt------------------
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	//*************Relaciones**************************
	//========= Relacion 1xN User-Reserva =========
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")  //clave foranea
	private User user;	
    //---------------Constructores-------------------
	public Reserva(){
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumeroReserva() {
		return numeroReserva;
	}
	public void setNumeroReserva(Long numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
	public Integer getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(Integer numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
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
	
	//**********Getters Setters************************
	
}
