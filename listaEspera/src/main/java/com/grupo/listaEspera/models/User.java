package com.grupo.listaEspera.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user")
public class User {
	//------------ Id ----------------------------------
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	//********** Atributos o Campos ********************
	private String nombre;
	private String apellido;
	private String numTelefono;
	
	@Size(min=6,max=200)
	@Email
	private String email;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
   
    //========= Relacion 1xN User-Reserva =========
  	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
  	private List<Reserva> reservaList;
 
    //-------------------------------------------------------
        
    
	public User() {
	}
	public User(String nombre, String apellido, String numTelefono, @Size(min = 6, max = 200) @Email String email,
			List<Reserva> reservaList) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numTelefono = numTelefono;
		this.email = email;
		this.reservaList = reservaList;
	}

	public List<Reserva> getReservaList() {
		return reservaList;
	}
	public void setReservaList(List<Reserva> reservaList) {
		this.reservaList = reservaList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}
   