package io.github.wilsoncastro.backend.model.repository;

import io.github.wilsoncastro.backend.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(" select l from Log l where upper(l.tipo) like upper(:tipo)")
    Page<Log> buscarPorTipo(
        @Param("tipo") String tipo,
        Pageable pageable
    );
}
