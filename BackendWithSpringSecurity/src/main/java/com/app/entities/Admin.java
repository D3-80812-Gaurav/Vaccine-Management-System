package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends UserEntity {
	@Column(length = 20)
	private String name;
	
	{
		this.role=Role.ADMIN;
	}

	public Admin(String email,String name,String password) {
		this.name=name;
		super.setPassword(password);
		super.setEmail(email);
	}
}
