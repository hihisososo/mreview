
package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> , MovieListByQueryDslRepository {


//    @Query("select m, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
//            "left outer join Review  r on r.movie = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);

    /*@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m and mi.deleted = false " +
            "left outer join Review  r on r.movie = m group by m,mi")
    Page<Object[]> getListPage(Pageable pageable);*/

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left join MovieImage mi on mi.movie = m and mi.inum = (select max(i2.inum) from MovieImage i2 where i2.movie = m) " +
            "left outer join Review  r on r.movie = m " +
            "where m.deleted = false group by m,mi")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left join MovieImage mi on mi.movie = m and mi.inum = (select max(i2.inum) from MovieImage i2 where i2.movie = m) " +
            "left outer join Review  r on r.movie = m " +
            " where m.mno = :mno group by m,mi")
    List<Object[]> getMovieWithAll(Long mno);


    @Modifying
    @Query("update Movie m set m.deleted = true where m.mno = :mno")
    void softDelete(Long mno);
}
