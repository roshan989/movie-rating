package com.movies.rating.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "movies")
@Data
@Setter
public class Movies implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("tconst")
	@Id
	@Column(name = "tconst",nullable = false)
	String tconst;
	
	@JsonProperty("titleType")
	@Column(name = "titleType")
	String titleType;
	
	@JsonProperty("primaryTitle")
	@Column(name = "primaryTitle")
	String primaryTitle;
	
	@JsonProperty("runtimeMinutes")
	@Column(name = "runtimeMinutes",nullable = false)
	int runtimeMinutes;
	
	@JsonProperty("generes")
	@Column(name = "generes")
	String generes;
	
//	@JsonIgnore
	@OneToOne(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Rating rating;


}
