package com.movies.rating.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CombineData {

	@JsonProperty("tconst")
	@Column(name="tconst")
	String tconst;
	
	@JsonProperty("primaryTitle")
	String primaryTitle;
	
	@JsonProperty("generes")
	String generes;
	
	@JsonProperty("averageRating")
	double averageRating;
	
	@JsonProperty("numVotes")
	int numVotes;
}
