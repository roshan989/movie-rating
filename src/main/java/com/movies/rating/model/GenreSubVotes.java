package com.movies.rating.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenreSubVotes {
	
	@JsonProperty("Genre")
	String genres;
	
	List<GenereMovie> movies;
	
	@JsonProperty("Total")
	Long subTotal;
}
