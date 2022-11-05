package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.List;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

/*    @Modifying
    @Query("update MovieImage mi set mi.deleted = true where mi.movie.mno = :mno")
    void deleteByMno(long mno);*/

    List<MovieImage> findAllByMovie(Movie movie);

}
