package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plat")
public class Plat extends Met{
	@Id
	@Column(length = 50,nullable = false)
	private String nom;
	private float prix;

}
