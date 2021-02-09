package com.example.demo.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name="Ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numero;	
	private LocalDate date;
	private int nbCouvert;
	private float addition;
	@ManyToOne
	private Client client;
	@ManyToOne
	private com.example.demo.models.Tables tables;
	@ManyToMany
	@JoinTable(name = "Compose")
	@JsonIgnore
	private List<Met> mets ;
}
