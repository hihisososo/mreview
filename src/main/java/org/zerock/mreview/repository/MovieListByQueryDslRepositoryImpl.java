package org.zerock.mreview.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.mreview.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class MovieListByQueryDslRepositoryImpl extends QuerydslRepositorySupport implements MovieListByQueryDslRepository {

    public MovieListByQueryDslRepositoryImpl() {
        super(Movie.class);
    }

    @Override
    public Page<Object[]> getListPageByJPQL(Pageable pageable) {
        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        QMovieImage movieImage = QMovieImage.movieImage;


        JPQLQuery<MovieImage> query = from(movieImage);
        query.leftJoin(movie).on(movie.eq(movieImage.movie));
        JPQLQuery<Tuple> tuple = query
                .select(movie, movieImage,
                        JPAExpressions.select(review.grade.avg()).from(review).where(review.movie.eq(movieImage.movie)),
                        JPAExpressions.select(review.countDistinct()).from(review).where(review.movie.eq(movieImage.movie))

                )
                .where(movieImage.inum.in(JPAExpressions.select(movieImage.inum.min()).from(movieImage).where(movie.eq(movieImage.movie))
                        .groupBy(movie, movieImage.movie))
                ).orderBy(movie.mno.desc());
        tuple.groupBy(movie);
        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        log.info(result);

        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}
