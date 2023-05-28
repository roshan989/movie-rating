package com.movies.rating.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="ratings")
@Data
@Setter
public class Rating implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="tconst",nullable = false)
	String tconst;
	@Column(name="averageRating")
	double averageRating;
	@Column(name="numVotes")
	int numVotes;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    @PrimaryKeyJoinColumn(name="tconst", referencedColumnName="tconst")
    private Movies movies;

}
