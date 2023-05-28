package com.movies.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movies.rating.model.CombineData;
import com.movies.rating.model.GenreSubVotes;
import com.movies.rating.model.Movies;
import com.movies.rating.service.MoviesService;

@RestController
@RequestMapping("/api/v1")
public class RatingController {
	@Autowired
	MoviesService moviesService;

	@GetMapping("/longest-duration-movies")
	public List<Movies> longestDuration() {
		return moviesService.findTopTen();
	}

	@PostMapping("/new-movie")
	public String addMovie(@RequestBody Movies movie) {
		return moviesService.saveMovie(movie);
	}

	@GetMapping("top-rated-movies")
	public List<CombineData> topRated() {
		return moviesService.topRated();
	}

	@GetMapping("genre-movies-with-subtotals")
	public List<GenreSubVotes> movieByGenereSubTotal() {
		return moviesService.findGenereBySubVotes();
	}

	@PostMapping("update-runtime-minutes")
	public String increaseRunTime() {
		return moviesService.increaseRunTime();
	}

}
