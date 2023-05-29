package com.movies.rating.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.movies.rating.dao.MovieDao;
import com.movies.rating.model.CombineData;
import com.movies.rating.model.Movies;
import com.movies.rating.model.Rating;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceTests {

	@InjectMocks
	MoviesService moviesService;

	@Mock
	MovieDao movieDao;

	@Test
	public void findTopTenTest() throws Exception{
		Movies movie = dummyMovie();
		List<Movies> movies = new ArrayList<Movies>();
		movies.add(movie);
		when(movieDao.findTopTen()).thenReturn(movies);
		var list = moviesService.findTopTen();
		assertEquals(movies, list);
	}


	
	@Test
	public void findTopTenCatchTest() throws Exception{
		Mockito.when(movieDao.findTopTen()).thenThrow(new RuntimeException());
		moviesService.findTopTen();
	}
	
	@Test
	public void saveMovieIfTest() {
		Optional<Movies> movie=Optional.ofNullable(null);
		when(movieDao.findById(Mockito.anyString())).thenReturn(movie);
		when(movieDao.saveAndFlush(dummyMovie())).thenReturn(dummyMovie());
		var response= moviesService.saveMovie(dummyMovie());
		assertEquals(response, "Success");
	}
	
	@Test
	public void saveMovieElseTest() {
		Optional<Movies> movie=Optional.ofNullable(dummyMovie());
		when(movieDao.findById(Mockito.anyString())).thenReturn(movie);
		var response= moviesService.saveMovie(dummyMovie());
		assertEquals(response, "Already Exists");
	}
	
	@Test
	public void saveMovieCatchTest() {
//		Optional<Movies> movie=Optional.ofNullable(new Movies());
		when(movieDao.findById(Mockito.anyString())).thenThrow(new RuntimeException());
		var response= moviesService.saveMovie(dummyMovie());
		assertEquals( "Failed to Save",response);
	}
	
	private Movies dummyMovie() {
		String dummy = "dummy";
		Movies movie = new Movies();
		Rating rating = new Rating();
		rating.setAverageRating(0);
		rating.setNumVotes(0);
		rating.setTconst(dummy);
		movie.setGeneres(dummy);
		movie.setPrimaryTitle(dummy);
		movie.setRuntimeMinutes(1);
		movie.setTconst(dummy);
		movie.setTitleType(dummy);
		movie.setRating(rating);
		return movie;
	}
	
	@Test
	public void topRatedTest() {
		var list= new ArrayList<Movies>();
		list.add(dummyMovie());
		CombineData c=new CombineData();
		when(movieDao.findTopRated()).thenReturn(list);
		assertNotEquals(c, moviesService.topRated());
	}

}
