package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieListByQueryDslRepository {
    Page<Object[]> getListPageByJPQL(Pageable pageable);
}
