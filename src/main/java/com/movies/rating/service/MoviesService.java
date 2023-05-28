package com.movies.rating.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.rating.dao.MovieDao;
import com.movies.rating.model.CombineData;
import com.movies.rating.model.GenereMovie;
import com.movies.rating.model.GenereVotes;
import com.movies.rating.model.GenreSubVotes;
import com.movies.rating.model.Movies;
import com.movies.rating.model.Rating;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoviesService {

	final int documentory = 15;
	final int animation = 30;
	final int rest = 45;

	@Autowired
	MovieDao movieDao;

	
	public List<Movies> findTopTen() {
		var list = movieDao.findTopTen();
//		try {
//			list = list.subList(0, 10);
//		} catch (Exception e) {
//			log.error("List have less than 10 records or error in record");
//		}
		return list;
	}

	public String saveMovie(Movies movie) {
		try {
			if (movieDao.findById(movie.getTconst()).isEmpty()) {

				Rating r = movie.getRating();
				r.setTconst(movie.getTconst());
				movie.setRating(r);
				Movies a = movieDao.saveAndFlush(movie);
//				Rating rSave=ratingDao.saveAndFlush(r);

				log.info("Movie is saved with id :{}", a.getTconst(), r.toString());
				return "Success";
			} else {
				return "Already Exists";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed while saving movie");
			return "Failed to Save";
		}
	}

	public List<CombineData> topRated() {
		List<Movies> list = movieDao.findTopRated();
		log.info(" List size :{}", list.size());
		List<CombineData> cDataList = new ArrayList<>();
		list.stream().forEach((x) -> {
			var cData = new CombineData();
			cData.setTconst(x.getTconst());
			cData.setPrimaryTitle(x.getPrimaryTitle());
			cData.setGeneres(x.getGeneres());
			cData.setNumVotes(x.getRating().getNumVotes());
			cData.setAverageRating(x.getRating().getAverageRating());
			cDataList.add(cData);
		});

		return cDataList;
	}

	public List<GenreSubVotes> findGenereBySubVotes() throws IndexOutOfBoundsException {
		List<Movies> movies = movieDao.findAllMovies();
		List<GenereVotes> generes = movieDao.findVotesByGenere();
		List<GenreSubVotes> finalList = new ArrayList<GenreSubVotes>();

		log.debug(" =================== genere from Genere" + generes.get(0).getGeneres());
		log.debug(" =================== genere from movie" + movies.get(0).getGeneres());
		try {

			for (var x : generes) {
				var genreSubVote = new GenreSubVotes();
				var movieList = new ArrayList<GenereMovie>();
				genreSubVote.setGenres(x.getGeneres());

				for (var y : movies) {
					if (y.getGeneres().contentEquals(x.getGeneres())) {
						log.info("came under if\n ");
						var m = new GenereMovie();
						m.setNumVotes(y.getRating().getNumVotes());
						m.setPrimaryTitle(y.getPrimaryTitle());
						log.info("came under if\n :{}", m.getPrimaryTitle());
						movieList.add(m);
//					 movies.remove(y);
					}
				}
				genreSubVote.setMovies(movieList);
				genreSubVote.setSubTotal(x.getSubTotal());
				finalList.add(genreSubVote);
			}
		} catch (Exception e) {
			log.error("Error in retrieving list :{}", e.getMessage());
		}

		return finalList;
	}

	@Transactional
	public String increaseRunTime() {
		try {
			movieDao.increaseRunTime(documentory, animation, rest);
			return "RunTimes Updated";
		} catch (Exception e) {
			log.error("Failed to Update Time message = :{} \ntrace:{}", e.getMessage(), e.getStackTrace());
			return "RunTimes Updation Failed";
		}
	}

}
