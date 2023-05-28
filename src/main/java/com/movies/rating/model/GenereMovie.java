package com.movies.rating.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenereMovie {
	
	@JsonProperty("primaryTitle")
	String primaryTitle;
	
	@JsonProperty("numVotes")
	int numVotes;
}
