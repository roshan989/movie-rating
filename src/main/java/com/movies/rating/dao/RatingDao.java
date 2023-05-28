package com.movies.rating.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movies.rating.model.Movies;
import com.movies.rating.model.Rating;

@Repository
public interface RatingDao extends JpaRepository<Rating, Movies> {

}
