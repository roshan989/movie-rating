package com.movies.rating.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movies.rating.model.GenereVotes;
import com.movies.rating.model.Movies;

@Repository
public interface MovieDao extends JpaRepository<Movies, String>{
	
	
    @Query(value="SELECT * FROM movies m ORDER BY m.runtime_minutes DESC limit 10 ",nativeQuery = true)
	List<Movies> findTopTen();
    
    @Query("SELECT m FROM Movies m JOIN Rating n ON m.tconst=n.tconst WHERE n.averageRating >6.0")
    List<Movies> findTopRated();
    
   @Query("SELECT m FROM Movies m JOIN Rating n ON m.tconst=n.tconst")
   List<Movies> findAllMovies();
   
   @Query(value="select m.generes,SUM(n.num_votes) as subtotal from movies m join ratings n on m.tconst=n.tconst group by m.generes",nativeQuery = true)
   List<GenereVotes> findVotesByGenere();

   @Modifying
   @Query("UPDATE Movies m SET m.runtimeMinutes = CASE WHEN m.generes = 'Documentary' THEN m.runtimeMinutes + ?1 "
          + "WHEN m.generes = 'Animation' THEN m.runtimeMinutes + ?2 ELSE m.runtimeMinutes + ?3 END")
   void increaseRunTime(int doc, int animation, int rest);
   
//   @Query("ALTER TABLE")
//   void inCreaseRunTime();
  
}

