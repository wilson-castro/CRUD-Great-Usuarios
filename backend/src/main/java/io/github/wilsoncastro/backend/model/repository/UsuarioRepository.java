package io.github.wilsoncastro.backend.model.repository;

import io.github.wilsoncastro.backend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(" select u from Usuario u where upper(u.nome) like upper(:nome) and u.cpf like :cpf and u.rg like :rg ")
    Page<Usuario> buscarPorNomeCpfRg(
            @Param("nome") String nome,
            @Param("cpf") String cpf,
            @Param("rg") String rg,
            Pageable pageable
    );

    @Query(" SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.cpf = :cpf ")
    boolean existsByCpf( @Param("cpf") String cpf);

    @Query(" SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.rg = :rg ")
    boolean existsByRg( @Param("rg") String rg);

}
