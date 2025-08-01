package repository;

import entity.Categoria;
import entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

    List<Categoria> findByNomeContaining(String parte);

    List<Categoria> findAllByOrderByNomeAsc();

    boolean existsByNome(String nome);
}
